package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.country.CountryPayloadRequest;
import com.example.ecommercebackend.model.payload.country.CountryPayloadResponse;
import com.example.ecommercebackend.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryPayloadResponse>> getAllShippingCountries() {
        return ResponseEntity.status(OK)
                .body(countryService.findAllCountries());
    }

    @PostMapping
    public ResponseEntity<String> createCountry(@RequestBody CountryPayloadRequest countryPayloadRequest) {
        return ResponseEntity.status(CREATED)
                .body(countryService.addCountryToShippingCountries(countryPayloadRequest));
    }
}
