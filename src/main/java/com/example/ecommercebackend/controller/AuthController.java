package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.login.LoginRequest;
import com.example.ecommercebackend.model.payload.login.LoginResponse;
import com.example.ecommercebackend.model.payload.refreshtoken.RefreshTokenRequest;
import com.example.ecommercebackend.model.payload.registration.RegistrationPayloadRequest;
import com.example.ecommercebackend.model.payload.registration.RegistrationPayloadResponse;
import com.example.ecommercebackend.service.AuthService;
import com.example.ecommercebackend.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

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

    @PostMapping("/refresh/token")
    public ResponseEntity<LoginResponse> refreshTokens(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.status(ACCEPTED)
                .body(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
