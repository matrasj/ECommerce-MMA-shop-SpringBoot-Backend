package com.example.ecommercebackend.service;

import com.example.ecommercebackend.mapper.CountryPayloadResponseMapper;
import com.example.ecommercebackend.model.entity.Country;
import com.example.ecommercebackend.model.payload.country.CountryPayloadRequest;
import com.example.ecommercebackend.model.payload.country.CountryPayloadResponse;
import com.example.ecommercebackend.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private static final String SUCCESSFULLY_COUNTRY_ADDING = "Success";
    private final CountryRepository countryRepository;

    public List<CountryPayloadResponse> findAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryPayloadResponseMapper::mapToCountryPayloadResponse)
                .collect(Collectors.toList());
    }

    public String addCountryToShippingCountries(CountryPayloadRequest countryPayloadRequest) {
        countryRepository.save(Country.builder()
                .code(countryPayloadRequest.getCode())
                .name(countryPayloadRequest.getName()).build());
        
        return SUCCESSFULLY_COUNTRY_ADDING;
    }
}
