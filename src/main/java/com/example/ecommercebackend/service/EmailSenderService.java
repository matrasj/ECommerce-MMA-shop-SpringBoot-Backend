package com.example.ecommercebackend.service;

import com.example.ecommercebackend.config.AppConfig;
import com.example.ecommercebackend.model.entity.ConfirmationToken;
import com.example.ecommercebackend.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final AppConfig appConfig;

    @Async
    public void sendConfirmationEmail(User user, ConfirmationToken token) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("mma.shop@gmail.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Email confirmation");
        simpleMailMessage.setText("Hi, " + user.getUsername() + "\nClick here to confirm your email: "
        + appConfig.getUrl() + "/api/v1/auth/confirmation?token=" + token.getToken());

        javaMailSender.send(simpleMailMessage);

    }
}
