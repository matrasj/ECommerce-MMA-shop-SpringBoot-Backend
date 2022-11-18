package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.jwt.JwtTokenProvider;
import com.example.ecommercebackend.model.entity.User;
import com.example.ecommercebackend.model.entity.UserPrincipal;
import com.example.ecommercebackend.model.payload.login.LoginRequest;
import com.example.ecommercebackend.repository.UserRepository;
import com.example.ecommercebackend.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase
@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @MockBean
    AuthService authService;

    @MockBean
    UserDetailsService userService;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        reset(userService);
    }


    @Test
    void login() throws Exception {
        User user = getUser();
        assertAll("User fields test",
                () -> assertEquals("Kuba", user.getFirstName(), "First name failed"),
                () -> assertEquals("Matras", user.getLastName(), "Last name failed"),
                () -> assertEquals("username", user.getUsername(), "Username failed"),
                () -> assertEquals("password", user.getPassword(), "Password filed"),
                () -> assertEquals("jkob.matras@gmail.com", user.getEmail(), "Email failed")
        );

        UserPrincipal userPrincipal = new UserPrincipal(user);
        LoginRequest loginRequest = LoginRequest.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        String loginRequestAsString = objectMapper.writeValueAsString(loginRequest);
        given(authenticationManager.authenticate(any()))
                .willReturn(new UsernamePasswordAuthenticationToken(userPrincipal, userPrincipal.getPassword()));


        mockMvc.perform(
                post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestAsString)
                        .with(csrf())
        ).andExpect(status().isAccepted());



    }

    private User getUser() {
        return User.builder()
                .id(1L)
                .firstName("Kuba")
                .lastName("Matras")
                .email("jkob.matras@gmail.com")
                .username("username")
                .password("password")
                .build();

    }
}