package com.pomehi.kafka;

import com.pomehi.dto.Cat;
import com.pomehi.dto.Toy;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@Component
public class MyKafkaProd {

    KafkaTemplate<String, Cat> kafkaTemplate;

    public MyKafkaProd(KafkaTemplate<String, Cat> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        Cat cat = Cat.builder()
                .name("Микаса")
                .age(2)
                .favoriteToy(Toy.builder()
                        .name("Мышка")
                        .build())
                .build();
        ListenableFuture<SendResult<String, Cat>> loh = kafkaTemplate.send("loh", cat);
        loh.completable().thenAccept(action -> {
            ProducerRecord<String, Cat> pr = action.getProducerRecord();
            RecordMetadata rm = action.getRecordMetadata();
            StringBuilder sb = new StringBuilder();
            sb.append("Топик: ").append(pr.topic()).append("\n");
            sb.append("Партиция: ").append(rm.partition()).append("\n");
            sb.append("Офсет: ").append(rm.offset()).append("\n");
            sb.append("Сообщение: ").append(pr.value()).append("\n");

            System.out.println(sb);
        });
    }
}
