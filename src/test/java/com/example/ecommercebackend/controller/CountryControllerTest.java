package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.mapper.CountryPayloadResponseMapper;
import com.example.ecommercebackend.model.entity.Country;
import com.example.ecommercebackend.model.payload.country.CountryPayloadResponse;
import com.example.ecommercebackend.service.CountryService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class CountryControllerTest {
    @Test
    void getAllShippingCountries() throws Exception {

    }
    @Test
    void createCountry() {




    }
}