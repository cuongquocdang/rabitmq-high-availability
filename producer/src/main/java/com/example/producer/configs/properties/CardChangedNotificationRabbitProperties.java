package com.example.producer.configs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "card-changed-notification")
@Data
public class CardChangedNotificationRabbitProperties {

    private String notificationExchange;

    private QueueConfigInfo statusChanged;

    @Data
    public static class QueueConfigInfo {

        private String key;
    }
}
