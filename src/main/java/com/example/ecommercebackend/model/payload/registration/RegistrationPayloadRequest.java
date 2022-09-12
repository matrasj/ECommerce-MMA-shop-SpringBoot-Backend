package com.example.ecommercebackend.model.payload.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegistrationPayloadRequest {
    private String username;
    private String password;
    private String email;
}
