package com.example.rocket_saas.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.rocket_saas.authentication.LoginRequest;
import com.example.rocket_saas.dto.ApiResponse;
import com.example.rocket_saas.user.Role;
import com.example.rocket_saas.user.UserAsso;
import com.example.rocket_saas.user.UserRepository;
import com.example.rocket_saas.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import com.example.rocket_saas.authentication.JwtAuthResponse;
import com.example.rocket_saas.authentication.SignupRequest;



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


    // === ADMIN LOGIN ===
    @PostMapping("/admin/signin")
    public ResponseEntity<?> authenticateAdminUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtAuthResponse( jwtService.generateToken(userDetails)));
    }

    // === ADMIN REGISTER ===
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/admin/signup")
    public ResponseEntity<?> registerAdminUser(@RequestBody SignupRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ApiResponse("Email already exists!"));
        }

        UserAsso newUser = new UserAsso(
                userRequest.getEmail(),
                encoder.encode(userRequest.getPassword())
        );

        newUser.setRole(Role.ASSO_ADMIN);

        userRepository.save(newUser);
        System.out.println("Useradmin created");
        String message = "UserAdmin registered successfully!";
        return ResponseEntity.ok(new ApiResponse(message));

    }

    //To be deleted, super_admin will be created directly in the database
    // === SUPER ADMIN SIGNUP ===
//    @PostMapping("/superadmin/signup")
//    public ResponseEntity<?> registerSuperAdmin(@RequestBody SignupRequest userRequest) {
//        if (userRepository.existsByEmail(userRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body("Error: Username is already taken!");
//        }
//
//        UserAsso newUser = new UserAsso(
//                userRequest.getEmail(),
//                encoder.encode(userRequest.getPassword())
//        );
//
//        newUser.setRole(Role.SUPER_ADMIN);
//
//        userRepository.save(newUser);
//
//        return ResponseEntity.ok("User registered successfully!");
//    }

    @PostMapping("/superadmin/signin")
    public ResponseEntity<?> superAdminLogin(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtAuthResponse resp = new JwtAuthResponse(jwtService.generateToken(userDetails));

        return ResponseEntity.ok(resp);
    }



}
