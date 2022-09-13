package com.example.ecommercebackend.model.payload.purchase;

import com.example.ecommercebackend.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderItemPayload {
    private String name;
    private String size;
    private String brandName;
    private BigDecimal price;
    private String imagePath;
    private int amount;
    private Long productId;
}
