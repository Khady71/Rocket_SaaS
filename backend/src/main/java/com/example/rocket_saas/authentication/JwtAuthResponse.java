package com.example.rocket_saas.authentication;

public class JwtAuthResponse {
    private String accessToken;
    private String refreshToken;

    public JwtAuthResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
