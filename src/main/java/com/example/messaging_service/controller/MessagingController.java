package com.example.messaging_service.controller;

import com.example.messaging_service.config.RabbitMQConfig;
import com.example.messaging_service.entity.MessagingDetails;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessagingController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @PostMapping("/notify")
    public ResponseEntity<String> sendMessage(@RequestBody MessagingDetails details) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getQueueName(), details);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }
}
