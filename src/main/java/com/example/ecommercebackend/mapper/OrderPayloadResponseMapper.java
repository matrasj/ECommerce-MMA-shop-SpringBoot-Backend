package com.example.ecommercebackend.mapper;

import com.example.ecommercebackend.model.entity.Order;
import com.example.ecommercebackend.model.payload.order.OrderPayloadResponse;

public class OrderPayloadResponseMapper {
    public static OrderPayloadResponse mapToOrderPayloadResponse(Order order) {
        return OrderPayloadResponse.builder()
                .id(order.getId())
                .orderTrackingNumber(order.getOrderTrackingNumber())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .totalPrice(order.getTotalPrice())
                .totalQuantity(order.getTotalQuantity())
                .build();
    }
}
