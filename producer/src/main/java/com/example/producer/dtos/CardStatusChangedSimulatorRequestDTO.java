package com.example.producer.dtos;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CardStatusChangedSimulatorRequestDTO implements Serializable {

    private String cardStatus;
}
