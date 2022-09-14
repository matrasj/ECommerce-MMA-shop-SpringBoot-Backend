package com.example.ecommercebackend.model.payload.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserPayloadResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
