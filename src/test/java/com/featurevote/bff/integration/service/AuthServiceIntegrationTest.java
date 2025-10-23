package com.featurevote.bff.integration.service;

import com.featurevote.bff.AbstractIntegrationTest;
import com.featurevote.bff.resource.dto.LoginRequest;
import com.featurevote.bff.resource.dto.LoginResponse;
import com.featurevote.bff.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
public class AuthServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AuthService authService;

    @Test
    public void testLoginWithValidCredentials() throws Exception {
        // Implement the test logic for valid login credentials
        // Use MockMvc to perform a POST request to the login endpoint
        // Assert that the response status is OK and contains the expected data
        LoginRequest loginRequest = new LoginRequest(
                "testuser@gmail.com", "testpassword");
        LoginResponse response = authService.login(loginRequest);

        assert response.getMessage().equals("Login successful for user:testuser@gmail.com");
        assert response.getToken() != null;
        assertNotNull(response);

    }

    @Test
    public void testLoginWithInvalidCredentials() throws Exception {
        // Implement the test logic for invalid login credentials
        // Use MockMvc to perform a POST request to the login endpoint
        // Assert that the response status is OK and contains the expected error message
        LoginRequest loginRequest = new LoginRequest(
                "invaliduser@gmail.com", "invalidpassword");
        LoginResponse response = authService.login(loginRequest);

        assert response.getMessage().equals("Invalid username or password");
        assert response.getToken() == null;
        assertNotNull(response);
    }

    @Test
    public void testBadRequestForEmptyCredentials() throws Exception {
        // Implement the test logic for empty credentials
        // Use MockMvc to perform a POST request to the login endpoint
        // Assert that the response status is OK and contains the expected error message
        LoginRequest loginRequest = new LoginRequest("", "");
        LoginResponse response = authService.badRequest(loginRequest);

        assert response.getMessage().equals("Username and password must not be empty");
        assert response.getToken() == null;
        assertNotNull(response);
    }

    @Test
    public void testLogout() throws Exception {
        // Implement the test logic for logout
        // Use MockMvc to perform a POST request to the logout endpoint
        // Assert that the response status is OK and contains the expected success message
        String response = authService.logout();

        assert response.equals("Logout successful");
        assertNotNull(response);
    }

}
