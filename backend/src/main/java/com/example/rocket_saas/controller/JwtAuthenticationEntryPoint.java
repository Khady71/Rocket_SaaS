package com.example.rocket_saas.controller;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, java.io.IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String message = "Invalid email or password.";  // default

        if (authException.getCause() instanceof DisabledException) {
            message = "Account is disabled.";
        } else if (authException.getCause() instanceof LockedException) {
            message = "Account is locked.";
        } else if (authException instanceof BadCredentialsException) {
            message = "Invalid email or password.";
        } else if (authException instanceof CredentialsExpiredException) {
            message = "Credentials expired.";
        }

        response.getWriter().write("""
            {
                "error": "UNAUTHORIZED",
                "message": "%s",
                "timestamp": "%s"
            }
            """.formatted(message, Instant.now()));
    }
}
