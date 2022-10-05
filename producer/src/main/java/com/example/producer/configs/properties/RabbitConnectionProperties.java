package com.example.producer.configs.properties;

import lombok.Data;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Data
public class RabbitConnectionProperties {

    private boolean sslEnabled;

    private int port;

    private String username;
    private String password;
    private String host;
    private String virtualHost;

    public CachingConnectionFactory buildConnection() {
        var rabbitConnectionUri = String.format("%s://%s:%s@%s:%d/%s",
                this.sslEnabled ? "amqps" : "amqp",
                this.username,
                this.password,
                this.host,
                this.port,
                this.virtualHost);
        var connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri(rabbitConnectionUri);
        return connectionFactory;
    }
}
