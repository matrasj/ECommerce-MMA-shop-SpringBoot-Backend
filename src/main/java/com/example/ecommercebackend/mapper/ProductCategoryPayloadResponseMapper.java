package com.example.ecommercebackend.mapper;

import com.example.ecommercebackend.model.entity.ProductCategory;
import com.example.ecommercebackend.model.payload.productcategory.ProductCategoryPayloadResponse;

public class ProductCategoryPayloadResponseMapper {
    public static ProductCategoryPayloadResponse mapToProductCategoryPayloadResponse(ProductCategory productCategory) {
        return new ProductCategoryPayloadResponse(productCategory.getId(), productCategory.getName());
    }
}
