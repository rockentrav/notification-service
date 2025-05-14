package com.example.notificationservice.consumer;

import com.example.notificationservice.model.UserEvent;
import com.example.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class UserEventConsumer {

    private final EmailService emailService;

    public UserEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(UserEvent event) {
        System.out.println("Получено событие: " + event);

        switch (event.getType()) {
            case "CREATE" -> emailService.sendAccountCreated(event.getEmail());
            case "DELETE" -> emailService.sendAccountDeleted(event.getEmail());
            default -> System.out.println("Неизвестный тип события: " + event.getType());
        }
    }
}
