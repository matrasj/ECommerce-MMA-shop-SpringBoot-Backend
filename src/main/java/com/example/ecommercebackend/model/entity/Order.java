package com.example.ecommercebackend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @ManyToOne(cascade = {
            DETACH,
            MERGE,
            PERSIST,
            REFRESH
    })
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

//    @OneToMany(mappedBy = "order", cascade = ALL)
//    private Set<OrderItem> orderItems = new HashSet<>();
}
