package com.example.ecommercebackend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "amount")
    private int amount;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(cascade = {
            DETACH,
            MERGE,
            PERSIST,
            REFRESH
    })
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;


}
