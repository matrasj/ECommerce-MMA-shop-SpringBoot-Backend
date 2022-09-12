package com.example.ecommercebackend.service;

import com.example.ecommercebackend.model.entity.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConfirmationTokenFactory {
    public static ConfirmationToken createConfirmationTokenInstance() {
        return ConfirmationToken.builder()
                .token(generateRandomToken())
                .createdAt(LocalDateTime.now())
                .expireAt(LocalDateTime.now().plusMinutes(15))
                .build();
    }

    private static String generateRandomToken() {
        return UUID.randomUUID().toString();
    }
}
