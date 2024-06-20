package com.greenconnect.user_service.config.rabittmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUserDeletedMessage(Long userId) {
        rabbitTemplate.convertAndSend("user.delete.queue", userId);
    }
}
