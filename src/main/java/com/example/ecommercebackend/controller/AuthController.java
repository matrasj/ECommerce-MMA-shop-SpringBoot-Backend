package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.login.LoginRequest;
import com.example.ecommercebackend.model.payload.login.LoginResponse;
import com.example.ecommercebackend.model.payload.refreshtoken.RefreshTokenRequest;
import com.example.ecommercebackend.model.payload.registration.RegistrationPayloadRequest;
import com.example.ecommercebackend.model.payload.registration.RegistrationPayloadResponse;
import com.example.ecommercebackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/registration")
    public ResponseEntity<RegistrationPayloadResponse> registerUser(@RequestBody RegistrationPayloadRequest request) {
        return ResponseEntity.status(CREATED)
                .body(authService.createUser(request));
    }

    @GetMapping("/confirmation")
    public ResponseEntity<String> confirmAccount(@RequestParam String token) {
        return ResponseEntity.status(ACCEPTED)
                .body(authService.confirmAccountByToken(token));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(ACCEPTED)
                .body(authService.login(loginRequest));
    }



}
