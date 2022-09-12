package com.example.ecommercebackend.mapper;

import com.example.ecommercebackend.model.entity.Product;
import com.example.ecommercebackend.model.payload.product.ProductPayloadResponse;

public class ProductPayloadResponseMapper {
    public static ProductPayloadResponse mapToProductPayloadResponse(Product product) {
        return ProductPayloadResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .size(product.getSize())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .brandName(product.getBrandName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .lastUpdated(product.getLastUpdated())
                .imagePath(product.getImagePath())
                .productCategoryName(product.getProductCategory().getName())
                .build();
    }
}
