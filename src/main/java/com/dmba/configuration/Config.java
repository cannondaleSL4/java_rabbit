package com.dmba.configuration;

import com.dmba.rabbit.UsersOrderConsumer;
import com.dmba.repository.UsersOrderRepository;
import com.dmba.repository.wrapper.UsersOrderRepositoryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

/**
 * Configuration class for setting up beans and other configuration-related elements.
 */
@Configuration
public class Config {

    @Value("${spring.rabbitmq.name}")
    private String queue;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private UsersOrderConsumer usersOrderConsumer;

    /**
     * Creates a UsersOrderRepositoryWrapper bean.
     *
     * @param usersOrderRepository the UsersOrderRepository instance to be wrapped
     * @return a new UsersOrderRepositoryWrapper instance
     */
    @Bean
    public UsersOrderRepositoryWrapper getUsersOrderRepositoryWrapper(@Autowired UsersOrderRepository usersOrderRepository) {
        return new UsersOrderRepositoryWrapper(usersOrderRepository);
    }

}
