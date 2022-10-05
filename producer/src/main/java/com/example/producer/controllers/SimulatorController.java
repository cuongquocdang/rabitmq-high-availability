package com.example.producer.controllers;

import com.example.producer.dtos.CardStatusChangedMessageDTO;
import com.example.producer.dtos.CardStatusChangedSimulatorRequestDTO;
import com.example.producer.services.CardChangedNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("v1/simulators")
@RequiredArgsConstructor
public class SimulatorController {

    private final CardChangedNotificationService cardChangedNotificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void sendMessage(@RequestBody CardStatusChangedSimulatorRequestDTO requestDTO) {
        var message = new CardStatusChangedMessageDTO(requestDTO.getCardStatus(), ZonedDateTime.now(ZoneId.systemDefault()));
        cardChangedNotificationService.publishMessage(message);
    }

}
