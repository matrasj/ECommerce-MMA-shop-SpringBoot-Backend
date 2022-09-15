package com.example.ecommercebackend.model.payload.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderPayloadResponse {
    private Long id;
    private String orderTrackingNumber;
    private Date createdAt;
    private BigDecimal totalPrice;
    private String status;
    private int totalQuantity;
}
