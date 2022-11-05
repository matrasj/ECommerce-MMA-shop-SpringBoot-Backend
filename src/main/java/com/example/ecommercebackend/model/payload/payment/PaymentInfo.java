package com.example.ecommercebackend.model.payload.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentInfo {
    @NotNull(message = "Amount is required")
    private int amount;
    @NotNull(message = "Currency is required")
    private String currency;
}
