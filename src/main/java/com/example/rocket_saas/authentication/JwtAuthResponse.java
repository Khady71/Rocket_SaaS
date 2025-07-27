package com.example.rocket_saas.authentication;

public class JwtAuthResponse {
    private String accessToken;
    private String refreshToken;

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
