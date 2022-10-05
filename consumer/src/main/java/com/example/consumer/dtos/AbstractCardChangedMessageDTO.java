package com.example.consumer.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class AbstractCardChangedMessageDTO implements Serializable {

    protected String uuid;
    protected String timestamp;
}
