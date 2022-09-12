package com.example.ecommercebackend.model.payload.productcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryPayloadResponse {
    private Long id;
    private String name;
}
