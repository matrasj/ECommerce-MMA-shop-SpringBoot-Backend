package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.country.CountryPayloadResponse;
import com.example.ecommercebackend.model.payload.state.StatePayloadResponse;
import com.example.ecommercebackend.service.CountryService;
import com.example.ecommercebackend.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/states")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StateController {
    private final StateService stateService;

    @GetMapping("/country/{countryName}")
    public ResponseEntity<Set<StatePayloadResponse>> getAllStateByCountryName(@PathVariable String countryName) {
        return ResponseEntity.status(OK)
                .body(stateService.findAllStatesByCountryName(countryName));
    }
}
