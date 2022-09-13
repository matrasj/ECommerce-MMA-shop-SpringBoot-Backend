package com.example.ecommercebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class StateNotFoundException extends RuntimeException{
    public StateNotFoundException(String message) {
        super(message);
    }
}
