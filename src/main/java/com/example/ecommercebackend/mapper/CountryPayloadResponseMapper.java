package com.example.ecommercebackend.mapper;

import com.example.ecommercebackend.model.entity.Country;
import com.example.ecommercebackend.model.payload.country.CountryPayloadResponse;

public class CountryPayloadResponseMapper {
    public static CountryPayloadResponse mapToCountryPayloadResponse(Country country) {
        return CountryPayloadResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}
