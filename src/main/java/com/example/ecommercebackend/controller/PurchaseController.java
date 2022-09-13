package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.purchase.PurchasePayloadRequest;
import com.example.ecommercebackend.model.payload.purchase.PurchasePayloadResponse;
import com.example.ecommercebackend.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class PurchaseController {
    private final PurchaseService purchaseService;
    @PostMapping
    public ResponseEntity<PurchasePayloadResponse> makePurchase(@RequestBody PurchasePayloadRequest purchasePayloadRequest) {
        return ResponseEntity.status(CREATED)
                .body(purchaseService.makePurchase(purchasePayloadRequest));
    }
}
