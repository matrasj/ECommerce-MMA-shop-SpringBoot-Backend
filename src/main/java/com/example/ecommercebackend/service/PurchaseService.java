package com.example.ecommercebackend.service;

import com.example.ecommercebackend.exception.CountryNotFoundException;
import com.example.ecommercebackend.exception.ProductNotFoundException;
import com.example.ecommercebackend.exception.StateNotFoundException;
import com.example.ecommercebackend.model.entity.Address;
import com.example.ecommercebackend.model.entity.Customer;
import com.example.ecommercebackend.model.entity.Order;
import com.example.ecommercebackend.model.entity.OrderItem;
import com.example.ecommercebackend.model.payload.purchase.*;
import com.example.ecommercebackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private static final String COUNTRY_NOT_FOUND_MESSAGE = "Not found country %s";
    private static final String STATE_NOT_FOUND_MESSAGE = "Not found state %s";
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Not found product with id %d";
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public PurchasePayloadResponse makePurchase(PurchasePayloadRequest purchasePayloadRequest) {
        CustomerPayload customerPayload = purchasePayloadRequest.getCustomerPayload();
        AddressPayload addressPayload = purchasePayloadRequest.getAddressPayload();
        List<OrderItemPayload> orderItemPayloadList = purchasePayloadRequest.getOrderItemPayloadList();
        OrderPayload orderPayload = purchasePayloadRequest.getOrderPayload();

        //Building customer
        Customer customer = buildCustomerByPayload(customerPayload);

        //Building address
        Address address = buildAddressByPayload(addressPayload);

        //Building order items
        Set<OrderItem> orderItems = buildOrderItemsByPayload(orderItemPayloadList);

        //Building order
        Order order = buildOrderPayload(orderPayload);

        orderItems.forEach((orderItem -> orderItem.setOrder(order)));

        order.setOrderItems(orderItems);
        order.setCustomer(customer);
        order.setAddress(address);

        orderRepository.save(order);
        return new PurchasePayloadResponse(order.getOrderTrackingNumber());

    }

    private Customer buildCustomerByPayload(CustomerPayload customerPayload) {
        return Customer.builder()
                .firstName(customerPayload.getFirstName())
                .lastName(customerPayload.getLastName())
                .phoneNumber(customerPayload.getPhoneNumber())
                .email(customerPayload.getEmail())
                .build();
    }

    private Address buildAddressByPayload(AddressPayload addressPayload) {
        return Address.builder()
                .address(addressPayload.getAddress())
                .city(addressPayload.getCity())
                .country(countryRepository
                        .findByName(addressPayload.getCountryName())
                        .orElseThrow(() -> new CountryNotFoundException(String.format(COUNTRY_NOT_FOUND_MESSAGE, addressPayload.getCountryName())))
                        )
                .state(stateRepository
                        .findByName(addressPayload.getStateName())
                        .orElseThrow(() -> new StateNotFoundException(String.format(STATE_NOT_FOUND_MESSAGE, addressPayload.getCountryName())))
                        )
                .zipCode(addressPayload.getZipCode())
                .build();
    }

    private Set<OrderItem> buildOrderItemsByPayload(List<OrderItemPayload> orderItemPayloadList) {
        return orderItemPayloadList
                .stream()
                .map((orderItemPayload -> OrderItem.builder()
                        .name(orderItemPayload.getName())
                        .size(orderItemPayload.getSize())
                        .brandName(orderItemPayload.getBrandName())
                        .price(orderItemPayload.getPrice())
                        .imagePath(orderItemPayload.getImagePath())
                        .amount(orderItemPayload.getAmount())
                        .product(productRepository.findById(orderItemPayload.getProductId())
                                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, orderItemPayload.getProductId()))))
                        .build()
                ))
                .collect(Collectors.toSet());
    }

    private Order buildOrderPayload(OrderPayload orderPayload) {
        return Order.builder()
                .orderTrackingNumber(generateOrderTrackingNumber())
                .totalQuantity(orderPayload.getTotalQuantity())
                .totalPrice(orderPayload.getTotalPrice())
                .build();
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
