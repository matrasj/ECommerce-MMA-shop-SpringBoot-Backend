package com.example.ecommercebackend.service;

import com.example.ecommercebackend.mapper.StatePayloadResponseMapper;
import com.example.ecommercebackend.model.payload.state.StatePayloadResponse;
import com.example.ecommercebackend.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateService {
    private final StateRepository stateRepository;

    public Set<StatePayloadResponse> findAllStatesByCountryName(String countryName) {
        return stateRepository.findAllByCountryName(countryName)
                .stream()
                .map(StatePayloadResponseMapper::mapToStatePayloadResponse)
                .collect(Collectors.toSet());
    }
}
