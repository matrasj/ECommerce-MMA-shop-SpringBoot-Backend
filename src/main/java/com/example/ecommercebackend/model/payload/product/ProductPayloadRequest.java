package com.example.ecommercebackend.model.payload.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPayloadRequest {
    private String size;
    private String name;
    private String description;
    private String brandName;
    private int quantity;
    private BigDecimal price;
    private String imagePath;
    private Long productCategoryId;
}
