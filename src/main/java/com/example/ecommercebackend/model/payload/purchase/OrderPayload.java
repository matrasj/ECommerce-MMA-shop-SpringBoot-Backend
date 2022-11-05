package com.example.ecommercebackend.model.payload.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderPayload {
    @NotBlank(message = "Total quantity is required")
    private int totalQuantity;
    @NotBlank(message = "Total price is required")
    private BigDecimal totalPrice;
}
