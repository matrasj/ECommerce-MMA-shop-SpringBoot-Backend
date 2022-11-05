package com.example.ecommercebackend.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException exception) {
        Map<String, String> invalidArgumentsMap = new HashMap<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors
                .forEach((fieldError -> {
                    invalidArgumentsMap.put(
                            fieldError.getField(),
                            fieldError.getDefaultMessage()
                    );
                }));

        return invalidArgumentsMap;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(BAD_REQUEST)
    public Map<String, String> handleCustomException(RuntimeException exception) {
        HashMap<String, String> exceptionWithMessage = new HashMap<>();
        exceptionWithMessage.put("error", exception.getMessage());

        return exceptionWithMessage;
    }
}
