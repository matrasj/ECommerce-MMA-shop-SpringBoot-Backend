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
                .authorUsername(review.getAuthor().getUsername())
                .build();
    }
}
