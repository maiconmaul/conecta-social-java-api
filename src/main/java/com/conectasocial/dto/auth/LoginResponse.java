package com.conectasocial.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    
    @JsonProperty("access_token")
    private String accessToken;
    
    // Constructors
    public LoginResponse() {}
    
    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    
    // Getters and Setters
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
