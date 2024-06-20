package com.greenconnect.postservice.config.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendPostDeletedMessage(Long postId) {
        rabbitTemplate.convertAndSend("post.delete.queue", postId);
    }
}
