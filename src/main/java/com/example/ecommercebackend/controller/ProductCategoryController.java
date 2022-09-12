package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.productcategory.ProductCategoryPayloadResponse;
import com.example.ecommercebackend.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/product-categories")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    @GetMapping
    public ResponseEntity<List<ProductCategoryPayloadResponse>> getProductCategories() {
        return ResponseEntity.status(OK)
                .body(productCategoryService.findAllProductCategories());
    }
}
