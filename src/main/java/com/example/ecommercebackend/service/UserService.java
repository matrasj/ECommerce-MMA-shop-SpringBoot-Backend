package com.example.ecommercebackend.service;

import com.example.ecommercebackend.exception.EmailAlreadyExistsException;
import com.example.ecommercebackend.exception.UsernameAlreadyExistsException;
import com.example.ecommercebackend.model.entity.ConfirmationToken;
import com.example.ecommercebackend.model.entity.User;
import com.example.ecommercebackend.model.payload.registration.RegistrationPayloadRequest;
import com.example.ecommercebackend.model.payload.registration.RegistrationPayloadResponse;
import com.example.ecommercebackend.model.payload.user.UserPayloadResponse;
import com.example.ecommercebackend.repository.ConfirmationTokenRepository;
import com.example.ecommercebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String USERNAME_ALREADY_EXISTS_MESSAGE = "User with username %s already exists";
    private static final String EMAIL_ALREADY_EXISTS_MESSAGE = "User with email %s already exists";
    private static final boolean DISABLED_ACCOUNT = false;
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;


    @Transactional
    public RegistrationPayloadResponse createNewUser(RegistrationPayloadRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(String.format(USERNAME_ALREADY_EXISTS_MESSAGE, request.getUsername()));
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(String.format(EMAIL_ALREADY_EXISTS_MESSAGE, request.getEmail()));
        }

        ConfirmationToken confirmationTokenInstance
                = ConfirmationTokenFactory.createConfirmationTokenInstance();

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(DISABLED_ACCOUNT)
                .tokens(new HashSet<>())
                .build();

        userRepository.save(user);

        confirmationTokenInstance.setUser(user);
        user.getTokens().add(confirmationTokenInstance);

        confirmationTokenRepository.save(confirmationTokenInstance);
        emailSenderService.sendConfirmationEmail(user, confirmationTokenInstance);

        return new RegistrationPayloadResponse(confirmationTokenInstance.getToken());
    }
}
