package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.payment.PaymentInfo;
import com.example.ecommercebackend.model.payload.purchase.PurchasePayloadRequest;
import com.example.ecommercebackend.model.payload.purchase.PurchasePayloadResponse;
import com.example.ecommercebackend.service.PurchaseService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PurchaseController {
    private final PurchaseService purchaseService;
    @PostMapping
    public ResponseEntity<PurchasePayloadResponse> makePurchase(@RequestBody @Valid PurchasePayloadRequest purchasePayloadRequest) {
        return ResponseEntity.status(CREATED)
                .body(purchaseService.makePurchase(purchasePayloadRequest));
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
        return ResponseEntity.status(CREATED)
                .body(purchaseService.createPaymentIntent(paymentInfo));
    }
}
