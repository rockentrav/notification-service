package com.example.notificationservice;

import com.example.notificationservice.model.UserEvent;
import com.example.notificationservice.service.EmailService;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = "user-events")
public class KafkaListenerIntegrationTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @SpyBean
    private EmailService emailService;

    @Test
    void testKafkaListenerSendsEmailOnUserCreated() {

        Map<String, Object> producerProps = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class);

        var producerFactory = new DefaultKafkaProducerFactory<String, UserEvent>(producerProps);
        KafkaTemplate<String, UserEvent> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic("user-events");


        UserEvent event = new UserEvent("create", "test@example.com");
        kafkaTemplate.sendDefault(event);


        verify(emailService, timeout(5000)).sendEmail(
                "test@example.com",
                "Аккаунт создан",
                "Здравствуйте! Ваш аккаунт на сайте был успешно создан."
        );
    }
}