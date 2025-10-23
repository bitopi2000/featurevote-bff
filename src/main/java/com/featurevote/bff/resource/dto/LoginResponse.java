package com.featurevote.bff.resource.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class LoginResponse {
    private String message;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

}
