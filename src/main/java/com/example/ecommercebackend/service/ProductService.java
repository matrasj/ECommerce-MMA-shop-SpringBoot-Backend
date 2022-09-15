package com.example.ecommercebackend.service;

import com.example.ecommercebackend.exception.ProductNotFoundException;
import com.example.ecommercebackend.mapper.ProductMapper;
import com.example.ecommercebackend.mapper.ProductPayloadResponseMapper;
import com.example.ecommercebackend.model.entity.Product;
import com.example.ecommercebackend.model.payload.product.ProductPayloadRequest;
import com.example.ecommercebackend.model.payload.product.ProductPayloadResponse;
import com.example.ecommercebackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final String PRODUCTS_SAVE_SUCCESSFULLY_MESSAGE = "Successfully added products";
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Not found product with id %d";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public String addProductsList(List<ProductPayloadRequest> products) {
        products
                .stream()
                .map(productMapper::mapToProduct)
                .forEach(productRepository::save);

        return PRODUCTS_SAVE_SUCCESSFULLY_MESSAGE;
    }

    public List<ProductPayloadResponse> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductPayloadResponseMapper::mapToProductPayloadResponse)
                .collect(Collectors.toList());
    }

    public Page<ProductPayloadResponse> getProductsWithPagination(int pageSize, int pageNumber) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));

        return new PageImpl<>(
                productPage
                        .stream()
                        .map(ProductPayloadResponseMapper::mapToProductPayloadResponse)
                        .collect(Collectors.toList()),
                PageRequest.of(pageNumber, pageSize),
                productPage.getTotalElements()
        );
    }

    public Page<ProductPayloadResponse> getProductsWithPaginationByProductCategoryId(Long productCategoryId,
                                                                                     int pageSize,
                                                                                     int pageNumber) {
        Page<Product> productsByCategoryId
                = productRepository.findByProductCategoryId(productCategoryId, PageRequest.of(pageNumber, pageSize));

        return new PageImpl<>(
                productsByCategoryId
                        .stream()
                        .map(ProductPayloadResponseMapper::mapToProductPayloadResponse)
                        .collect(Collectors.toList()),
                PageRequest.of(pageNumber, pageSize),
                productsByCategoryId.getTotalElements()
        );
    }

    public Page<ProductPayloadResponse> getProductsWithPaginationByNameContainingKeyword(String keyword,
                                                                                         int pageSize,
                                                                                         int pageNumber) {
        Page<Product> productsByNameContaining
                = productRepository.findByNameContaining(keyword, PageRequest.of(pageNumber, pageSize));

        return new PageImpl<>(
                productsByNameContaining
                        .stream()
                        .map(ProductPayloadResponseMapper::mapToProductPayloadResponse)
                        .collect(Collectors.toList()),
                PageRequest.of(pageNumber, pageSize),
                productsByNameContaining.getTotalElements()
        );
    }

    public List<String> findAllUniqueBrands() {
        return productRepository
                .findAll()
                .stream()
                .map(Product::getBrandName)
                .distinct()
                .collect(Collectors.toList());
    }

    public ProductPayloadResponse findProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, productId)));

        return ProductPayloadResponseMapper.mapToProductPayloadResponse(product);
    }

    public Page<ProductPayloadResponse> getProductsWithPaginationByBrandName(String brandName, int pageSize, int pageNumber) {
        Page<Product> productsByBrandName
                = productRepository.findByBrandName(brandName, PageRequest.of(pageNumber, pageSize));

        return new PageImpl<>(
                productsByBrandName
                        .stream()
                        .map(ProductPayloadResponseMapper::mapToProductPayloadResponse)
                        .toList(),
                PageRequest.of(pageNumber, pageSize),
                productsByBrandName.getTotalElements()
        );
    }
}
