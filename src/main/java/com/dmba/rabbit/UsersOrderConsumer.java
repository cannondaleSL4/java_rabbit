package com.dmba.rabbit;

import com.dmba.dao.UsersOrder;
import com.dmba.dao.proto.UsersOrderProto;

import com.dmba.storage.Storage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;


/**
 * A Spring component that listens for messages on a RabbitMQ queue and processes them.
 */
@Component
@Slf4j
public class UsersOrderConsumer implements MessageListener {

    @Autowired
    private Storage storage;

    @Autowired
    private TaskExecutor taskExecutor;

    @Value("${spring.rabbitmq.name}")
    private String queue;

    /**
     * Converts a RabbitMQ message to a UsersOrderProto.UsersOrder object.
     *
     * @param message The RabbitMQ message to be converted.
     * @return The converted UsersOrderProto.UsersOrder object.
     * @throws IOException If an error occurs while parsing the message.
     */
    @SneakyThrows
    private UsersOrderProto.UsersOrder convertMessageToMyData(Message message) {
        log.debug("Stored data for queue: {}", queue);
        return UsersOrderProto.UsersOrder.parseFrom(message.getBody());
    }

    /**
     * Processes incoming messages from the RabbitMQ queue.
     *
     * @param message The RabbitMQ message to be processed.
     */
    @RabbitListener(queues = "${spring.rabbitmq.name}",  concurrency = "#{${spring.rabbitmq.listener.concurrency}}")
    public void onMessage(Message message) {
        taskExecutor.execute(() -> {
            UsersOrderProto.UsersOrder usersOrder = convertMessageToMyData(message);
            UsersOrder usersOrderEntity = new UsersOrder(usersOrder);
            // Save the data in the database
            storage.saveMessage(usersOrderEntity);
        });
    }
}