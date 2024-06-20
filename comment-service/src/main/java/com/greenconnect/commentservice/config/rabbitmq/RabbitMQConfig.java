package com.greenconnect.commentservice.config.rabbitmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public SimpleMessageListenerContainer postDeleteContainer(ConnectionFactory connectionFactory,
                                                              MessageListenerAdapter postDeleteListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("post.delete.queue");
        container.setMessageListener(postDeleteListenerAdapter);
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer userDeleteContainer(ConnectionFactory connectionFactory,
                                                              MessageListenerAdapter userDeleteListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("user.delete.queue");
        container.setMessageListener(userDeleteListenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter postDeleteListenerAdapter(CommentMessageListener listener) {
        return new MessageListenerAdapter(listener, "receivePostDeleteMessage");
    }

    @Bean
    public MessageListenerAdapter userDeleteListenerAdapter(CommentMessageListener listener) {
        return new MessageListenerAdapter(listener, "receiveUserDeleteMessage");
    }
}
