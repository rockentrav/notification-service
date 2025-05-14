package com.example.notificationservice.service;

public interface EmailService {

    void sendAccountCreated(String toEmail);

    void sendAccountDeleted(String toEmail);

    void sendEmail(String to, String subject, String body);

}
