package com.example.ecommercebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ProductCategoryNotFoundException extends RuntimeException{
    public ProductCategoryNotFoundException(String message) {
        super(message);
    }
}
