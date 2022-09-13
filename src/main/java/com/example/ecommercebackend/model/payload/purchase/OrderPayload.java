package com.example.ecommercebackend.model.payload.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderPayload {
    private int totalQuantity;
    private BigDecimal totalPrice;
}
