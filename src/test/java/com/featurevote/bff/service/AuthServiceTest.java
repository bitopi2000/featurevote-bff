package com.featurevote.bff.service;

import com.featurevote.bff.domain.User;
import com.featurevote.bff.repository.UserRepository;
import com.featurevote.bff.controller.dto.LoginRequest;
import com.featurevote.bff.controller.dto.LoginResponse;
import com.featurevote.bff.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final JwtUtil jwtUtil = mock(JwtUtil.class);
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService(userRepository, jwtUtil);
    }

    @Test
    public void loginSuccess() {
        // Given a valid login request
        String email = "testUser@gmail.com";
        String password = "testPassword";
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0VXNlckBnbWFpbC5jb20iLCJpYXQiOjE3ODMxMTYwMDAsImV4cCI6MTc4MzIwMjQwMH0.boxq5oh96FFF9wCox853ZJQaQSYkek0zENL4wJL4PpSqMtPrcUG20SHXyyvGH0qt";
        LoginRequest loginRequest = new LoginRequest(email, password);
        User user = new User();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);

        when(this.userRepository.findByEmail(email)).thenReturn(user);
        when(this.jwtUtil.generateToken(loginRequest.getEmail())).thenReturn(token);
        // When the login method is called
        LoginResponse response = this.authService.login(loginRequest);

        // Then the response should indicate success
        assertNotNull(response);
        assertEquals("Login successful for user:" + email, response.getMessage());
        assertNotNull(response.getToken());
    }

    @Test
    public void loginFailure() {
        // Given an invalid login request
        String email = "invalidUser@gmail.com";
        String password = "invalidPassword";
        LoginRequest loginRequest = new LoginRequest(email, password);

        when(this.userRepository.findByEmail(email)).thenReturn(null);
        LoginResponse response = this.authService.login(loginRequest);

        // Then the response should indicate failure
        assertNotNull(response);
        assertEquals("Invalid username or password", response.getMessage());
        assertNull(response.getToken());
    }

    @Test
    void badRequest() {
        // Given an empty login request
        LoginRequest loginRequest = new LoginRequest("", "");

        // When the badRequest method is called
        LoginResponse response = this.authService.badRequest(loginRequest);

        // Then the response should indicate a bad request
        assertNotNull(response);
        assertEquals("Username and password must not be empty", response.getMessage());
        assertNull(response.getToken());
    }

    @Test
    void logout() {
        // When the logout method is called
        String response = this.authService.logout();

        // Then the response should indicate a successful logout
        assertNotNull(response);
        assertEquals("Logout successful", response);
    }
}