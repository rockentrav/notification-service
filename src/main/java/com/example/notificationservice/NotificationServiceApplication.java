package com.example.notificationservice;

import com.example.notificationservice.config.DotenvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class NotificationServiceApplication {

    public static void main(String[] args) {
        new DotenvLoader();
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

}
