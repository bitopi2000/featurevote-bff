package com.featurevote.bff.integration.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.featurevote.bff.AbstractIntegrationTest;
import com.featurevote.bff.domain.User;
import com.featurevote.bff.repository.UserRepository;
import com.featurevote.bff.resource.dto.LoginRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Testcontainers
public class AuthControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRole(User.Role.USER);
        user.setEmail("testuser@gmail.com");
        userRepository.save(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldLoginSuccessfully() throws Exception {
        // This test will be implemented in the future
        LoginRequest loginRequest = new LoginRequest(
                "testuser@gmail.com",
                "testpassword");
        this.mockMvc.perform(
                post("/api/auth/login")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void shouldReturnBadRequestForEmptyCredentials() throws Exception {
        // This test will be implemented in the future
        LoginRequest loginRequest = new LoginRequest("", "");
        this.mockMvc.perform(
                post("/api/auth/login")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Username and password must not be empty"));
    }

    @Test
    public void shouldReturnBadRequestForInvalidCredentials() throws Exception {
        // This test will be implemented in the future
        LoginRequest loginRequest = new LoginRequest(
                "invaliduser@gmail.com",
                "invalidpassword");
        this.mockMvc.perform(
                post("/api/auth/login")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Invalid username or password"));
    }

    @Test
    public void shouldLogoutSuccessfully() throws Exception {
        // This test will be implemented in the future
        this.mockMvc.perform(
                post("/api/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Logout successful"));
    }
}
