package com.example.ecommercebackend.model.payload.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerPayload {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
