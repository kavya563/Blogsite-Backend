/*
package com.blogsite.blog_site_user.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.blogsite.blog_site_user.entity.*;
import com.blogsite.blog_site_user.repository.RoleRepository;
import com.blogsite.blog_site_user.repository.UserRepository;
import com.blogsite.blog_site_user.security.JwtUtils;
import com.blogsite.blog_site_user.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtUtils jwtUtils;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }


    @Test
    public void testAuthenticateUser_Success() throws Exception {
        String username = "testUser";
        String password = "password";

        // Create a LoginRequest
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Create a UserDetailsImpl instance
        UserDetails userDetails = new UserDetailsImpl("1", username, "test@example.com", password, new HashSet<>());

        // Create an Authentication object with the UserDetailsImpl
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Mock the authentication manager to return the authenticated UserDetails
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("jwt-token");

        // Perform the login request
        mockMvc.perform(post("/blogsite/user/blogs/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    }


    @Test
    public void testAuthenticateUser_Failure() throws Exception {
        String username = "testUser";
        String password = "wrongPassword";
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Mock the authentication manager to throw an exception
        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Authentication failed"));

        // Perform the login request and expect a bad request response
        mockMvc.perform(post("/blogsite/user/blogs/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Authentication failed: Authentication failed")); // Optional: check the response message
    }


    @Test
    public void testRegisterUser_Success() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(encoder.encode(user.getPassword())).thenReturn("encodedPassword");

        Role role = new Role();
        role.setName(ERole.ROLE_USER);
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));

        mockMvc.perform(post("/blogsite/user/blogs/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testUser\", \"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));

        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegisterUser_UsernameExists() throws Exception {
        User user = new User();
        user.setUsername("existingUser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

        mockMvc.perform(post("/blogsite/user/blogs/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"existingUser\", \"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Username is already taken!"));
    }

    @Test
    public void testRegisterUser_EmailExists() throws Exception {
        User user = new User();
        user.setUsername("newUser");
        user.setEmail("existing@example.com");
        user.setPassword("password");

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        mockMvc.perform(post("/blogsite/user/blogs/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"newUser\", \"email\":\"existing@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Email is already in use!"));
    }
}
*/
