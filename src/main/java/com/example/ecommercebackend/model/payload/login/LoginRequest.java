package com.example.ecommercebackend.model.payload.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
