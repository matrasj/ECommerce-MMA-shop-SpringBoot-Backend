package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.product.ProductPayloadRequest;
import com.example.ecommercebackend.model.payload.product.ProductPayloadResponse;
import com.example.ecommercebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> addProducts(@RequestBody List<ProductPayloadRequest> products) {
        return ResponseEntity.status(CREATED)
                .body(productService.addProductsList(products));
    }

    @GetMapping("/brands")
    public ResponseEntity<List<String>> getProductBrands() {
        return ResponseEntity.status(OK)
                .body(productService.findAllUniqueBrands());
    }

    @GetMapping
    public ResponseEntity<List<ProductPayloadResponse>> findAllProducts() {
        return ResponseEntity.status(OK)
                .body(productService.getAllProducts());
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProductPayloadResponse>> findAllProductsWithPagination(@RequestParam int pageSize,
                                                                                      @RequestParam int pageNumber) {
        return ResponseEntity.status(OK)
                .body(productService.getProductsWithPagination(pageSize, pageNumber));
    }

    @GetMapping("/pagination/findByProductCategoryId")
    public ResponseEntity<Page<ProductPayloadResponse>> findProductsByProductCategoryId(@RequestParam Long productCategoryId,
                                                                                        @RequestParam int pageSize,
                                                                                        @RequestParam int pageNumber) {
        return ResponseEntity.status(OK)
                .body(productService.getProductsWithPaginationByProductCategoryId(productCategoryId, pageSize, pageNumber));
    }

    @GetMapping("/pagination/findByNameContainingKeyword")
    public ResponseEntity<Page<ProductPayloadResponse>> findProductsByNameContainingKeyword(@RequestParam String keyword,
                                                                                            @RequestParam int pageSize,
                                                                                            @RequestParam int pageNumber) {
        return ResponseEntity.status(OK)
                .body(productService.getProductsWithPaginationByNameContainingKeyword(keyword, pageSize, pageNumber));
    }

    @GetMapping("/pagination/findByBrandName")
    public ResponseEntity<Page<ProductPayloadResponse>> findProductsByBrandName(@RequestParam String brandName,
                                                                                @RequestParam int pageSize,
                                                                                @RequestParam int pageNumber) {
        return ResponseEntity.status(OK)
                .body(productService.getProductsWithPaginationByBrandName(brandName, pageSize, pageNumber));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductPayloadResponse> findProductById(@PathVariable Long productId) {
        return ResponseEntity.status(OK)
                .body(productService.findProductById(productId));
    }

}
