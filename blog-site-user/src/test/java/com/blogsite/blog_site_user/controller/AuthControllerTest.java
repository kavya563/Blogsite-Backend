/*
package com.blogsite.blog_site_user.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.blogsite.blog_site_user.commands.CreateUserCommand;
import com.blogsite.blog_site_user.entity.AppConstants;
import com.blogsite.blog_site_user.entity.User;
import com.blogsite.blog_site_user.repository.UserRepository;
import com.blogsite.blog_site_user.service.KafKaProducerService;
import kafka.utils.PasswordEncoder;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Collections;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private KafKaProducerService kafkaProducerService;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private CommandGateway commandGateway;

    private User signUpRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        signUpRequest = new User();
        signUpRequest.setUsername("newuser");
        signUpRequest.setEmail("user@example.com");
        signUpRequest.setPassword("password");
    }

    // Positive Test Case
    @Test
    public void testRegisterUser_Success() {
        // Mocking the scenario where username and email do not exist
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(false);

        // Act
        ResponseEntity<?> response = authController.registerUser(signUpRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("message", "User registered successfully"), response.getBody());
        verify(commandGateway).sendAndWait(any(CreateUserCommand.class));
    }

    // Negative Test Case - Username Already Taken
    @Test
    public void testRegisterUser_UsernameAlreadyTaken() {
        // Mocking the scenario where the username already exists
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(true);

        // Act
        ResponseEntity<?> response = authController.registerUser(signUpRequest);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(Collections.singletonMap("message", "Error: Username is already taken!"), response.getBody());
        verify(kafkaProducerService).userActions(contains("Error: Username is already taken!"), eq(AppConstants.TOPIC_USER_REGISTER_FAIL));
        verify(commandGateway, never()).sendAndWait(any());
    }

    // Negative Test Case - Email Already Taken
    @Test
    public void testRegisterUser_EmailAlreadyTaken() {
        // Mocking the scenario where the email already exists
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(true);

        // Act
        ResponseEntity<?> response = authController.registerUser(signUpRequest);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(Collections.singletonMap("message", "Error: Email is already in use!"), response.getBody());
        verify(kafkaProducerService).userActions(contains("Error: Email is already taken!"), eq(AppConstants.TOPIC_USER_REGISTER_FAIL));
        verify(commandGateway, never()).sendAndWait(any());
    }
}
*/
