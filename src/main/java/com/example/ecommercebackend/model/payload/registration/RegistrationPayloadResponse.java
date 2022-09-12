package com.example.ecommercebackend.model.payload.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationPayloadResponse {
    private String confirmationToken;
}
