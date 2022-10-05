package com.example.producer.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
public class CardStatusChangedMessageDTO extends AbstractCardChangedMessageDTO implements Serializable {

    private String cardStatus;

    public CardStatusChangedMessageDTO(String cardStatus, ZonedDateTime eventDateTime) {
        super(eventDateTime);
        this.cardStatus = cardStatus;
    }
}
