package com.example.ecommercebackend.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(cascade = {
            REFRESH,
            MERGE,
            DETACH,
            PERSIST
    })
    private User author;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(cascade = {
            REFRESH,
            MERGE,
            DETACH,
            PERSIST
    })
    private Product product;




}


