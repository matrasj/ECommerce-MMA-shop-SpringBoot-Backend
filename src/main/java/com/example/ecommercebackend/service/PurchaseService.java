package com.example.ecommercebackend.service;

import com.example.ecommercebackend.exception.CountryNotFoundException;
import com.example.ecommercebackend.exception.ProductNotFoundException;
import com.example.ecommercebackend.exception.StateNotFoundException;
import com.example.ecommercebackend.model.entity.Address;
import com.example.ecommercebackend.model.entity.Customer;
import com.example.ecommercebackend.model.entity.Order;
import com.example.ecommercebackend.model.entity.OrderItem;
import com.example.ecommercebackend.model.payload.payment.PaymentInfo;
import com.example.ecommercebackend.model.payload.purchase.*;
import com.example.ecommercebackend.repository.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service

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

    @Autowired
    public PurchaseService(CustomerRepository customerRepository,
                           AddressRepository addressRepository,
                           CountryRepository countryRepository,
                           StateRepository stateRepository,
                           ProductRepository productRepository,
                           OrderRepository orderRepository,
                           @Value("${stripe.key.secret}") String secretKey) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        Stripe.apiKey = secretKey;
    }



    @Value("${stripe.key.secret}")
    private String secretKey;



    @Transactional
    public PurchasePayloadResponse makePurchase(PurchasePayloadRequest purchasePayloadRequest) {
        CustomerPayload customerPayload = purchasePayloadRequest.getCustomerPayload();
        AddressPayload addressPayload = purchasePayloadRequest.getAddressPayload();
        List<OrderItemPayload> orderItemPayloadList = purchasePayloadRequest.getOrderItemPayloadList();
        OrderPayload orderPayload = purchasePayloadRequest.getOrderPayload();

        //Building customer
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerPayload.getEmail());
        Customer customer = optionalCustomer.orElseGet(() -> buildCustomerByPayload(customerPayload));

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
                .status("Waiting")
                .build();
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

    public String createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("card");

        Map<String, Object> params = new HashMap<>();

        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethods);

        return PaymentIntent.create(params).toJson();
    }
}
