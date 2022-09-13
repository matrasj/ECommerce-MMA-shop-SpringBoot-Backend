package com.example.ecommercebackend.model.payload.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CountryPayloadResponse {
    private Long id;
    private String name;
}
