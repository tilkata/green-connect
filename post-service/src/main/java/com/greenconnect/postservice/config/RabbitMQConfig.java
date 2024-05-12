package com.greenconnect.postservice.config;

//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class RabbitMQConfig {
//
//    @Value("${rabbitmq.exchange.name}")
//    private String exchange;
//
//    @Value("${rabbitmq.queue.name}")
//    private String queue;
//
//    @Value("${rabbitmq.routing.key}")
//    private String routingKey;
//
//    @Bean
//    public Queue queue() {
//        return new Queue(queue, true);
//    }
//
//    @Bean
//    public TopicExchange exchange() {
//        return new TopicExchange(exchange);
//    }
//
//    public Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with(routingKey);
//    }
//}
