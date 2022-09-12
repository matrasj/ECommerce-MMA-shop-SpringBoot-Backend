package com.example.ecommercebackend.mapper;

import com.example.ecommercebackend.exception.ProductCategoryNotFoundException;
import com.example.ecommercebackend.model.entity.Product;
import com.example.ecommercebackend.model.payload.product.ProductPayloadRequest;
import com.example.ecommercebackend.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper {
    private static final String PRODUCT_CATEGORY_NOT_FOUND_MESSAGE = "Not found product category with id --> %d";
    private final ProductCategoryRepository productCategoryRepository;

    public Product mapToProduct(ProductPayloadRequest productPayloadRequest) {
        return Product.builder()
                .size(productPayloadRequest.getSize())
                .name(productPayloadRequest.getName())
                .description(productPayloadRequest.getDescription())
                .brandName(productPayloadRequest.getBrandName())
                .quantity(productPayloadRequest.getQuantity())
                .price(productPayloadRequest.getPrice())
                .imagePath(productPayloadRequest.getImagePath())
                .productCategory(
                         productCategoryRepository.findById(productPayloadRequest.getProductCategoryId())
                                .orElseThrow(() -> new ProductCategoryNotFoundException(String.format(PRODUCT_CATEGORY_NOT_FOUND_MESSAGE, productPayloadRequest.getProductCategoryId())))
                )
                .build();
    }
}
