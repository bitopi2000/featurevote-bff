package com.featurevote.bff.service;

import com.featurevote.bff.domain.User;
import com.featurevote.bff.repository.UserRepository;
import com.featurevote.bff.controller.dto.LoginRequest;
import com.featurevote.bff.controller.dto.LoginResponse;
import com.featurevote.bff.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse(
                    "Invalid username or password",
                    null);
        }

        String token = jwtUtil.generateToken(loginRequest.getEmail());

        return new LoginResponse(
                "Login successful for user:" + loginRequest.getEmail(),
                token);
    }

    public LoginResponse badRequest(LoginRequest loginRequest) {
        return new LoginResponse(
                "Username and password must not be empty",
                null);
    }

    public String logout() {
        // Handle logout logic here
        // For now, we will just return a success message
        return "Logout successful";
    }
}
