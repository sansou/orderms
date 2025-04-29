package tech.buildrun.orderms.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    // RabbitMQ configuration can be added here if needed
    // For example, you can define RabbitTemplate, Queue, Exchange, etc.
    public static final String ORDER_CREATED_QUEUE = "btg-pactual-order-created;";

}
