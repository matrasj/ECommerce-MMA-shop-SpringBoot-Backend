package com.example.ecommercebackend.model.payload.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPayloadResponse {
    private Long id;
    private String size;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private String brandName;
    private Date createdAt;
    private Date lastUpdated;
    private String imagePath;
    private String productCategoryName;
}
