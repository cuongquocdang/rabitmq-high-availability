package com.example.consumer.configs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "card-changed-notification")
@Data
public class CardChangedNotificationRabbitProperties {

    private int messageTimeToLive;
    private int maxDeadThreshold;

    private String notificationExchange;
    private String technicalDeadLetterExchange;

    private QueueConfigInfo statusChanged;

    @Data
    public static class QueueConfigInfo {

        private String queue;
        private String deadLetterQueue;
        private String parkingLotQueue;
        private String key;
    }
}
