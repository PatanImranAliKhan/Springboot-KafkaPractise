package com.example.demo.Controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
public class ConsumerController {

    @KafkaListener(topics = "topic", groupId = "group-id")
    public void consume(String message)
    {
        System.out.println("message = " + message);
    }
}
