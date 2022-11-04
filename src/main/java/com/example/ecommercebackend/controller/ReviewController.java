package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.model.payload.review.ReviewPayloadRequest;
import com.example.ecommercebackend.model.payload.review.ReviewPayloadResponse;
import com.example.ecommercebackend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewPayloadRequest request) {
        return ResponseEntity.status(CREATED)
                .body(reviewService.createReview(request));
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<List<ReviewPayloadResponse>> getReviewsByProductId(@PathVariable Long productId) {
        return ResponseEntity.status(OK)
                .body(reviewService.findReviewsByProductId(productId));
    }

    @GetMapping("/current")
    public ResponseEntity<List<ReviewPayloadResponse>> getReviewsForCurrentUser() {
        return ResponseEntity.status(OK)
                .body(reviewService.findReviewsForCurrentUser());
    }


}
