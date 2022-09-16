package com.example.ecommercebackend.model.payload.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryPayloadRequest {
    private String name;
    private String code;
}
