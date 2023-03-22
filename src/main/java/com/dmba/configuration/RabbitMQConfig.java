package com.dmba.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class for setting up RabbitMQ-related beans.
 */
@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.name}")
    private String queue;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    /**
     * Creates a Queue bean with the specified queue name.
     *
     * @return a new Queue instance
     */
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    /**
     * Creates a TopicExchange bean with the specified exchange name.
     *
     * @return a new TopicExchange instance
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    /**
     * Creates a Binding bean to bind the queue and exchange using the specified routing key.
     *
     * @return a new Binding instance
     */
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }
}
