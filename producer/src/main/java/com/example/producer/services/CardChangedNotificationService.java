package com.example.producer.services;

import com.example.producer.dtos.CardStatusChangedMessageDTO;

public interface CardChangedNotificationService {

    void publishMessage(CardStatusChangedMessageDTO messageDTO);
}
