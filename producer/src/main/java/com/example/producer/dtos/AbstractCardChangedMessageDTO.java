package com.example.producer.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class AbstractCardChangedMessageDTO implements Serializable {

    protected String uuid;
    protected String timestamp;

    public AbstractCardChangedMessageDTO(ZonedDateTime eventDateTime) {
        this.uuid = UUID.randomUUID().toString();
        this.timestamp = eventDateTime.toOffsetDateTime().toString();
    }
}
