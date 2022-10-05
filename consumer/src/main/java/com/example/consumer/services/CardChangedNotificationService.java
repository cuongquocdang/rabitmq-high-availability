package com.example.consumer.services;

import org.springframework.amqp.core.Message;

public interface CardChangedNotificationService {

    @SuppressWarnings("unused")
    void listenMessage(Message message);

    void publishMessage(Message message);
}
