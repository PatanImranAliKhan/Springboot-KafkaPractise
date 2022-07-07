package com.example.demo.config;

import com.fasterxml.jackson.databind.JsonSerializable;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.*;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public Map<String,Object> producerconfig()
    {
        Map<String,Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return config;
    }

    @Bean
    public ProducerFactory<String,String> producerFactory()
    {
        return new DefaultKafkaProducerFactory<>(producerconfig());
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate()
    {
        return new KafkaTemplate<String,String>(producerFactory());
    }
}
