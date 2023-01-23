package com.pomehi.kafka;

import com.pomehi.dto.Cat;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@EnableKafka
@Slf4j
public class MyListener {

    @KafkaListener(topics = {"loh", "neloh"})
    public void listen(ConsumerRecord<String, Cat> message) {
        StringBuilder sb = new StringBuilder();
        sb.append("Топик: ").append(message.topic()).append("\n");
        sb.append("Партиция: ").append(message.partition()).append("\n");
        sb.append("Оффсет: ").append(message.offset()).append("\n");
        sb.append("Дата: ").append(message.timestamp()).append("\n");
        sb.append("Сообщение: ").append(message.value()).append("\n");

        log.info(sb.toString());
    }
}
