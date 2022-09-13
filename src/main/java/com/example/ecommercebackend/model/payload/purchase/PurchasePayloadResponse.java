package com.example.ecommercebackend.model.payload.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchasePayloadResponse {
    private String orderTrackingNumber;
}
