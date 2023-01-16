package com.pomehi.kafkaproducer.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.TopicPartitionOffset;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaProd {

    KafkaTemplate<String, String> kafkaTemplate;

    public MyKafkaProd(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("loh", message);
    }
}
