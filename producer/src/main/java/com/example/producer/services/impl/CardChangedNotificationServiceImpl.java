package com.example.producer.services.impl;

import com.example.producer.configs.properties.CardChangedNotificationRabbitProperties;
import com.example.producer.dtos.CardStatusChangedMessageDTO;
import com.example.producer.services.CardChangedNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardChangedNotificationServiceImpl implements CardChangedNotificationService {

    private final RabbitTemplate rabbitTemplate;
    private final CardChangedNotificationRabbitProperties rabbitProperties;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void publishMessage(CardStatusChangedMessageDTO messageDTO) {
        var messageConverted = new Message(objectMapper.writeValueAsBytes(messageDTO));
        log.info("RabbitMQ - processing to send card status changed message: {} and message properties: {}",
                new String(messageConverted.getBody(), StandardCharsets.UTF_8), messageConverted.getMessageProperties());
        rabbitTemplate.send(rabbitProperties.getNotificationExchange(), rabbitProperties.getStatusChanged().getKey(), messageConverted);
    }
}
