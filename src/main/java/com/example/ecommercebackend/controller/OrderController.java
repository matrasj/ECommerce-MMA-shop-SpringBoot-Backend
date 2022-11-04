package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.order.OrderPayloadResponse;
import com.example.ecommercebackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/current")
    public ResponseEntity<Page<OrderPayloadResponse>> getOrdersWithPaginationForCurrentUser(@RequestParam int pageSize,
                                                                                            @RequestParam int pageNumber) {
        return ResponseEntity.status(OK)
                .body(orderService.findOrdersForCurrentUser(pageSize, pageNumber));
    }
}
