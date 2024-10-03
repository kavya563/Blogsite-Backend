package com.blogsite.blog_site_user.controller;


import com.blogsite.blog_site_user.commands.CreateUserCommand;
import com.blogsite.blog_site_user.entity.*;
import com.blogsite.blog_site_user.repository.RoleRepository;
import com.blogsite.blog_site_user.repository.UserRepository;
import com.blogsite.blog_site_user.security.JwtUtils;
import com.blogsite.blog_site_user.security.UserDetailsImpl;
import com.blogsite.blog_site_user.service.KafKaProducerService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/blogsite/user/blogs")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
    JwtUtils jwtUtils;
	@Autowired
	KafKaProducerService kafkaProducerService;

	private CommandGateway commandGateway;
	JwtResponse jwtResponse=new JwtResponse();
	public AuthController(CommandGateway commandGateway) {
		this.commandGateway=commandGateway;
	}

	/**
	 * Authenticate User Credentials
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication == null || !authentication.isAuthenticated()) {
			throw new IllegalArgumentException("Authentication cannot be null or unauthenticated");
		}
		String jwt = jwtUtils.generateJwtToken(authentication);
		jwtResponse.setToken(jwt);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		kafkaProducerService.userActions(loginRequest.getUsername(), AppConstants.TOPIC_USER_LOGIN);
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail()));
	} catch (Exception e) {
		// Log the error if needed
		return ResponseEntity.badRequest().body("Authentication failed: " + e.getMessage());
	}
	}


	/**
	 * 
	 * Register new user
	 * 
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			kafkaProducerService.userActions("Error: Username is already taken!" + signUpRequest.getUsername(), AppConstants.TOPIC_USER_REGISTER_FAIL);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			kafkaProducerService.userActions("Error: Email is already taken!" + signUpRequest.getEmail(), AppConstants.TOPIC_USER_REGISTER_FAIL);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Error: Email is already in use!"));
		}
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		kafkaProducerService.userActions(signUpRequest.getUsername(), AppConstants.TOPIC_USER_REGISTER);
		CreateUserCommand createBlogCommand = new CreateUserCommand(
				signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				signUpRequest.getPassword()
		);
		commandGateway.sendAndWait(createBlogCommand);

		return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));
	}

		@GetMapping("/jwt/authentication")
	public ResponseEntity<?> getToken(){
		return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
	}


}
