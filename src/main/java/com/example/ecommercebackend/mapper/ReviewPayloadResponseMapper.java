package com.example.ecommercebackend.mapper;

import com.example.ecommercebackend.model.entity.Review;
import com.example.ecommercebackend.model.payload.review.ReviewPayloadResponse;

public class ReviewPayloadResponseMapper {
    public static ReviewPayloadResponse mapToReviewPayloadResponse(Review review) {
        return ReviewPayloadResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .lastUpdated(review.getLastUpdated())
                .authorFirstName(review.getAuthor().getFirstName())
                .authorLastName(review.getAuthor().getLastName())
                .authorUsername(review.getAuthor().getUsername())
                .productId(review.getProduct().getId())
                .productName(review.getProduct().getName())
                .productDescription(review.getProduct().getDescription())
                .productImagePath(review.getProduct().getImagePath())
                .productPrice(review.getProduct().getPrice())
                .productCategoryName(review.getProduct().getProductCategory().getName())
                .build();
    }
}
