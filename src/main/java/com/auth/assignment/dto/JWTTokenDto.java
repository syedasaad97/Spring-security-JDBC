package com.auth.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTTokenDto {

    @JsonProperty("token")
    private String token;

    public JWTTokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
