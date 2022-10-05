package com.example.consumer.services.impl;

import com.example.consumer.dtos.CardStatusChangedMessageDTO;
import com.example.consumer.services.CardChangedNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardChangedNotificationServiceImpl implements CardChangedNotificationService {

    private final ObjectMapper objectMapper;

    @Override
    @RabbitListener(
            containerFactory = "connectionFactory",
            queues = "${card-changed-notification.statusChanged.queue}")
    public void listenMessage(Message message) {
        log.info("RabbitMQ - received card status changed message: {} and message properties: {}",
                new String(message.getBody(), StandardCharsets.UTF_8), message.getMessageProperties());
    }

    @Override
    public void publishMessage(Message message) {
        var cardStatusChangedMessage = toMessagePayload(message);
        log.info("Processing to publish card status changed message: {}", cardStatusChangedMessage);
    }

    @SneakyThrows
    private CardStatusChangedMessageDTO toMessagePayload(Message message) {
        return objectMapper.readValue(message.getBody(), CardStatusChangedMessageDTO.class);
    }
}
