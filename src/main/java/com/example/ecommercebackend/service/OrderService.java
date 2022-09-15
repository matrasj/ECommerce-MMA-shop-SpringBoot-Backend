package com.example.ecommercebackend.service;

import com.example.ecommercebackend.mapper.OrderPayloadResponseMapper;
import com.example.ecommercebackend.model.entity.Order;
import com.example.ecommercebackend.model.entity.User;
import com.example.ecommercebackend.model.payload.order.OrderPayloadResponse;
import com.example.ecommercebackend.model.payload.purchase.OrderPayload;
import com.example.ecommercebackend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final AuthService authService;

    public Page<OrderPayloadResponse> findOrdersForCurrentUser(int pageSize, int pageNumber) {
        User currentUser = authService.getCurrentUser();
        Page<Order> ordersByCustomerEmail
                = orderRepository.findByCustomerEmail(currentUser.getEmail(), PageRequest.of(pageNumber, pageSize));

        return new PageImpl<>(
                ordersByCustomerEmail
                        .stream()
                        .map(OrderPayloadResponseMapper::mapToOrderPayloadResponse)
                        .toList(),
                PageRequest.of(pageNumber, pageSize),
                ordersByCustomerEmail.getTotalElements()
        );
    }
}
