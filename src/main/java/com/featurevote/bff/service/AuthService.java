package com.featurevote.bff.service;

import com.featurevote.bff.domain.User;
import com.featurevote.bff.repository.UserRepository;
import com.featurevote.bff.resource.dto.LoginRequest;
import com.featurevote.bff.resource.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse(
                    "Invalid username or password",
                    null);
        }

        return new LoginResponse(
                "Login successful for user:" + loginRequest.getEmail(),
                "dummy-token");
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
