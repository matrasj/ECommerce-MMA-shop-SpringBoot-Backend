package com.example.ecommercebackend.model.payload.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PurchasePayloadRequest {
    private AddressPayload addressPayload;
    private CustomerPayload customerPayload;
    private OrderPayload orderPayload;
    private List<OrderItemPayload> orderItemPayloadList = new ArrayList<>();
}
