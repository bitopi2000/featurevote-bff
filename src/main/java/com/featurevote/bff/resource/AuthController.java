package com.featurevote.bff.resource;

import com.featurevote.bff.resource.dto.LoginRequest;
import com.featurevote.bff.resource.dto.LoginResponse;
import com.featurevote.bff.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController (AuthService authService) {
        this.authService = authService;
    }

    // This class can be used to handle authentication-related endpoints
    // For example, you can add methods to handle login, logout, and user registration

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Endpoint for user login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getEmail().isEmpty() || loginRequest.getEmail().isBlank() || loginRequest.getEmail() == null
                || loginRequest.getPassword().isEmpty() || loginRequest.getPassword().isBlank() || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body(authService.badRequest(loginRequest));
        }

        return ResponseEntity.ok(
            authService.login(loginRequest));
    }

    @PostMapping("/logout")
    @Operation(summary = "User Logout", description = "Endpoint for user logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(authService.logout());
    }
}