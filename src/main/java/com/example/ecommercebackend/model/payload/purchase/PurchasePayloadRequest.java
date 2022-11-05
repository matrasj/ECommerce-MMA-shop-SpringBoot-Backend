package com.example.ecommercebackend.model.payload.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PurchasePayloadRequest {
    @NotNull(message = "Address for purchase is required")
    private AddressPayload addressPayload;
    @NotNull(message = "Customer is required for purchase")
    private CustomerPayload customerPayload;
    @NotNull(message = "Order data are required for purchase")
    private OrderPayload orderPayload;
    @NotNull(message = "Purchase must have some items inside")
    private List<OrderItemPayload> orderItemPayloadList = new ArrayList<>();
}
