package com.example.rocket_saas.controller;

import com.example.rocket_saas.user.UserAsso;
import com.example.rocket_saas.user.UserRepository;
import com.example.rocket_saas.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    // === LOGIN ===
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

    // === REGISTER ===
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User userRequest) {
        if (userRepository.existsByEmail(userRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        UserAsso newUser = new UserAsso(
                userRequest.getUsername(),
                encoder.encode(userRequest.getPassword())
        );

        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully!");
    }
}
