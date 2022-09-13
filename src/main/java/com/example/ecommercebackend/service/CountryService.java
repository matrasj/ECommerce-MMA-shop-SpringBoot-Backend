package com.example.ecommercebackend.service;

import com.example.ecommercebackend.mapper.CountryPayloadResponseMapper;
import com.example.ecommercebackend.model.payload.country.CountryPayloadResponse;
import com.example.ecommercebackend.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<CountryPayloadResponse> findAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryPayloadResponseMapper::mapToCountryPayloadResponse)
                .toList();
    }
}
