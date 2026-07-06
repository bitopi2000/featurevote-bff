package com.featurevote.bff.controller.dto;

import org.springframework.stereotype.Component;

@Component
public class StatusUpdateRequest {
    private String status;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
