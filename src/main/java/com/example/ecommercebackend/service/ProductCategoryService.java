package com.example.ecommercebackend.service;
import com.example.ecommercebackend.mapper.ProductCategoryPayloadResponseMapper;
import com.example.ecommercebackend.model.payload.productcategory.ProductCategoryPayloadResponse;
import com.example.ecommercebackend.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public List<ProductCategoryPayloadResponse> findAllProductCategories() {
        return productCategoryRepository.findAll()
                .stream()
                .map(ProductCategoryPayloadResponseMapper::mapToProductCategoryPayloadResponse)
                .collect(Collectors.toList());
    }
}
