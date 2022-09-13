package com.example.ecommercebackend.mapper;

import com.example.ecommercebackend.model.entity.State;
import com.example.ecommercebackend.model.payload.state.StatePayloadResponse;

public class StatePayloadResponseMapper {
    public static StatePayloadResponse mapToStatePayloadResponse(State state) {
        return StatePayloadResponse.builder()
                .id(state.getId())
                .name(state.getName())
                .build();
    }
}
