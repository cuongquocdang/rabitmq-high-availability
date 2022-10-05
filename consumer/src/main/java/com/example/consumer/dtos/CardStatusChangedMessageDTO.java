package com.example.consumer.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class CardStatusChangedMessageDTO extends AbstractCardChangedMessageDTO implements Serializable {

    private String cardStatus;
}
