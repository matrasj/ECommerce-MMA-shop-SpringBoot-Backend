package com.example.ecommercebackend.model.payload.review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReviewPayloadResponse {
    private Long id;
    private String content;
    private Date createdAt;
    private Date lastUpdated;
    private String authorFirstName;
    private String authorLastName;
    private String authorUsername;
    private String productName;
    private String productDescription;
    private String productImagePath;
    private BigDecimal productPrice;
    private String productCategoryName;
    private Long productId;
}
