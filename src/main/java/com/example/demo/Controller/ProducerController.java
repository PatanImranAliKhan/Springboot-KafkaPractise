package com.example.demo.Controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class ProducerController {

    @Autowired
    KafkaTemplate kafkaTemplate;

//    @PostMapping("/post")
//    public String get_details(@RequestBody String data)
//    {
//        kafkaTemplate.send("Topic",data);
//
//    }
    @GetMapping("/")
    public void sendToKafka() {
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send("topic","Hello Irfan");
        future.addCallback(new KafkaSendCallback<Integer, String>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                ProducerRecord<Integer, String> failed = ex.getFailedProducerRecord();
                System.out.println(ex);
            }

        });
    }
}
