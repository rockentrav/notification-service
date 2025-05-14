package com.example.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
//@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendAccountCreated(String toEmail) {
        sendEmail(toEmail, "Аккаунт создан", "Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
    }

    @Override
    public void sendAccountDeleted(String toEmail) {
        sendEmail(toEmail, "Аккаунт удалён", "Здравствуйте! Ваш аккаунт был удалён.");
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("kuzmenelena3@gmail.com");

        mailSender.send(message);
    }
}
