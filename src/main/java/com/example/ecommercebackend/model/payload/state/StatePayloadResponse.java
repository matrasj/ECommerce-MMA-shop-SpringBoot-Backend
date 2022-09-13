package com.example.ecommercebackend.model.payload.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StatePayloadResponse {
    private Long id;
    private String name;
}
