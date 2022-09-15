package com.example.ecommercebackend.service;

import com.example.ecommercebackend.exception.ConfirmationTokenAlreadyConfirmedException;
import com.example.ecommercebackend.exception.ConfirmationTokenAlreadyExpiredException;
import com.example.ecommercebackend.exception.ConfirmationTokenNotFoundExceptions;
import com.example.ecommercebackend.exception.EmailNotValidException;
import com.example.ecommercebackend.model.entity.ConfirmationToken;
import com.example.ecommercebackend.model.entity.User;
import com.example.ecommercebackend.model.payload.login.LoginRequest;
import com.example.ecommercebackend.model.payload.login.LoginResponse;
import com.example.ecommercebackend.model.payload.refreshtoken.RefreshTokenRequest;
import com.example.ecommercebackend.model.payload.registration.RegistrationPayloadRequest;
import com.example.ecommercebackend.model.payload.registration.RegistrationPayloadResponse;
import com.example.ecommercebackend.model.payload.user.UserPayloadResponse;
import com.example.ecommercebackend.repository.ConfirmationTokenRepository;
import com.example.ecommercebackend.repository.UserRepository;
import com.example.ecommercebackend.security.JwtProvider;
import com.example.ecommercebackend.validator.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final String EMAIL_NOT_VALID_MESSAGE = "Email %s is not valid";
    private static final String TOKEN_NOT_FOUND_MESSAGE = "Not found token --> %s";
    private static final String TOKEN_ALREADY_CONFIRMED_MESSAGE = "Token %s has been already confirmed";
    private static final String TOKEN_ALREADY_EXPIRED_MESSAGE = "Token %s has been already expired";
    private static final boolean ENABLE_ACCOUNT = true;
    private static final String SUCCESSFULLY_CONFIRMED_TOKEN = "Successfully email confirmation";
    private final UserService userService;
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailValidator emailValidator;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public RegistrationPayloadResponse createUser(RegistrationPayloadRequest request) {
        if (!emailValidator.test(request.getEmail())) {
            throw new EmailNotValidException(String.format(EMAIL_NOT_VALID_MESSAGE ,request.getEmail()));
        }

        return userService.createNewUser(request);
    }

    public String confirmAccountByToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ConfirmationTokenNotFoundExceptions(String.format(TOKEN_NOT_FOUND_MESSAGE, token)));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ConfirmationTokenAlreadyConfirmedException(String.format(TOKEN_ALREADY_CONFIRMED_MESSAGE, token));
        }

        if (confirmationToken.getExpireAt().isBefore(LocalDateTime.now())) {
            throw new ConfirmationTokenAlreadyExpiredException(String.format(TOKEN_ALREADY_EXPIRED_MESSAGE, token));
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationToken.getUser().setEnabled(ENABLE_ACCOUNT);

        confirmationTokenRepository.save(confirmationToken);

        return SUCCESSFULLY_CONFIRMED_TOKEN;
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
    }


    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return LoginResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        User currentUser = getCurrentUser();
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return LoginResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public UserPayloadResponse getCurrentUserData() {
        User currentUser = getCurrentUser();
        return UserPayloadResponse.builder()
                .id(currentUser.getId())
                .firstName(currentUser.getFirstName())
                .lastName(currentUser.getLastName())
                .email(currentUser.getEmail())
                .build();
    }
}
