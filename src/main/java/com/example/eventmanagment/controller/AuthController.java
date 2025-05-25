package com.example.eventmanagment.controller;

import com.example.eventmanagment.dto.auth.AuthResponse;
import com.example.eventmanagment.dto.auth.LoginRequest;
import com.example.eventmanagment.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // hapi 1: Authenticate user
        var user = service.authenticate(request.getEmail(), request.getPassword());

        // hapi 2: Generate token
        var token = service.generateToken(user);

        var authResponse = new AuthResponse(token, 86400000L); // 24h in milliseconds

        return ResponseEntity.ok(authResponse);
    }
}