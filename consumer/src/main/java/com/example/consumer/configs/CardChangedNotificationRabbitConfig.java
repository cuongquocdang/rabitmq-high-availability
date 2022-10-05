package com.example.consumer.configs;

import com.example.consumer.configs.properties.CardChangedNotificationRabbitProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CardChangedNotificationRabbitConfig {

    private final CardChangedNotificationRabbitProperties cardChangedNotificationRabbitProperties;

    @Bean
    public DirectExchange cardStatusChangeDirectExchange() {
        return new DirectExchange(cardChangedNotificationRabbitProperties.getNotificationExchange());
    }

    @Bean
    public TopicExchange technicalTopicExchange() {
        return new TopicExchange(cardChangedNotificationRabbitProperties.getTechnicalDeadLetterExchange());
    }

    @Bean
    public Queue cardStatusChangedQueue() {
        var cardStatusChangedQueueInfo =
                cardChangedNotificationRabbitProperties.getStatusChanged();
        return QueueBuilder
                .durable(cardStatusChangedQueueInfo.getQueue())
                .deadLetterExchange(cardChangedNotificationRabbitProperties.getTechnicalDeadLetterExchange())
                .deadLetterRoutingKey(cardStatusChangedQueueInfo.getDeadLetterQueue())
                .build();
    }

    @Bean
    public Queue cardStatusChangedDLQ() {
        var cardStatusChangedQueueInfo =
                cardChangedNotificationRabbitProperties.getStatusChanged();
        return QueueBuilder
                .durable(cardStatusChangedQueueInfo.getDeadLetterQueue())
                .deadLetterExchange(cardChangedNotificationRabbitProperties.getTechnicalDeadLetterExchange())
                .deadLetterRoutingKey(cardStatusChangedQueueInfo.getQueue())
                .ttl(cardChangedNotificationRabbitProperties.getMessageTimeToLive())
                .build();
    }

    @Bean
    public Queue cardStatusChangedPLQ() {
        return QueueBuilder
                .durable(cardChangedNotificationRabbitProperties.getStatusChanged().getParkingLotQueue())
                .build();
    }

    @Bean
    public Binding cardStatusChangedBinding(Queue cardStatusChangedQueue,
                                            DirectExchange cardNotificationDirectExchange) {
        return BindingBuilder.bind(cardStatusChangedQueue)
                .to(cardNotificationDirectExchange)
                .with(cardChangedNotificationRabbitProperties.getStatusChanged().getKey());
    }

    @Bean
    public Declarables cardStatusChangedTechnicalBindings(Queue cardStatusChangedQueue,
                                                          Queue cardStatusChangedDLQ,
                                                          Queue cardStatusChangedPLQ,
                                                          TopicExchange technicalTopicExchange) {
        return new Declarables(
                BindingBuilder
                        .bind(cardStatusChangedQueue)
                        .to(technicalTopicExchange)
                        .with(cardStatusChangedQueue.getName()),
                BindingBuilder
                        .bind(cardStatusChangedDLQ)
                        .to(technicalTopicExchange)
                        .with(cardStatusChangedDLQ.getName()),
                BindingBuilder
                        .bind(cardStatusChangedPLQ)
                        .to(technicalTopicExchange)
                        .with(cardStatusChangedPLQ.getName()));
    }
}
