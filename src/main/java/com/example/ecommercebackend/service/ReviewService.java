package com.example.ecommercebackend.service;

import com.example.ecommercebackend.exception.ProductNotFoundException;
import com.example.ecommercebackend.mapper.ReviewPayloadResponseMapper;
import com.example.ecommercebackend.model.entity.Product;
import com.example.ecommercebackend.model.entity.Review;
import com.example.ecommercebackend.model.entity.User;
import com.example.ecommercebackend.model.payload.review.ReviewPayloadRequest;
import com.example.ecommercebackend.model.payload.review.ReviewPayloadResponse;
import com.example.ecommercebackend.repository.ProductRepository;
import com.example.ecommercebackend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Not found product with id %d";
    private static final String SUCCESSFULLY_REVIEW_CREATION = "Successfully created review";
    private final AuthService authService;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public String createReview(ReviewPayloadRequest request) {
        User currentUser = authService.getCurrentUser();

        Product reviewingProduct
                = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, request.getProductId())));

        Review review = Review.builder()
                .author(currentUser)
                .product(reviewingProduct)
                .content(request.getContent())
                .build();

        reviewRepository.save(review);

        return SUCCESSFULLY_REVIEW_CREATION;
    }

    public List<ReviewPayloadResponse> findReviewsByProductId(Long productId) {
        List<Review> reviewsByProductId
                = reviewRepository.findAllByProductId(productId);

        return reviewsByProductId
                .stream()
                .map(ReviewPayloadResponseMapper::mapToReviewPayloadResponse)
                .collect(Collectors.toList());
    }
}

