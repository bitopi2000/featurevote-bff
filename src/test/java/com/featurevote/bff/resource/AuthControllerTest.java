package com.featurevote.bff.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.featurevote.bff.resource.dto.LoginRequest;
import com.featurevote.bff.resource.dto.LoginResponse;
import com.featurevote.bff.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private AuthService authService;

    @Test
    void shouldLoginSuccessfully() throws Exception {
        // Given
        String email = "testuser@gmail.com";
        String password = "testpassword";
        LoginRequest loginRequest = new LoginRequest(email, password);

        LoginResponse loginResponse = new LoginResponse("Login successful for user:" + email, "mock-jwt-token");

        when(this.authService.login(any(LoginRequest.class))).thenReturn(loginResponse);
        this.mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-jwt-token"));

    }

    @Test
    void shouldReturnBadRequestForEmptyCredentials() throws Exception {
        // Given
        LoginRequest loginRequest = new LoginRequest("", "");

        LoginResponse loginResponse = new LoginResponse("Username and password must not be empty", null);

        when(this.authService.badRequest(any(LoginRequest.class))).thenReturn(loginResponse);
        this.mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Username and password must not be empty"));
    }

    @Test
    void logout() throws Exception {
        when(authService.logout()).thenReturn("Logout successful");

        this.mockMvc.perform(post("/api/auth/logout")
                        .header("Authorization", "Bearer mock-jwt-token"))
                .andExpect(status().isOk());
    }
}