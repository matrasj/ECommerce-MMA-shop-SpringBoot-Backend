package com.example.ecommercebackend.model.payload.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReviewPayloadRequest {
    @NotBlank(message = "Content is required")
    private String content;
    private Long productId;
}
