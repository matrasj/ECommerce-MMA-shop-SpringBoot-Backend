package com.example.ecommercebackend.model.payload.review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String authorUsername;
}
