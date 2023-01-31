package com.pomehi.kafka;

import com.pomehi.dto.Cat;
import com.pomehi.service.CatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
@RequiredArgsConstructor
public class MyListener {

    private final CatService catService;

    @KafkaListener(topics = {"loh", "neloh"})
    public void listen(ConsumerRecord<String, Cat> message) {
        StringBuilder sb = new StringBuilder();
        sb.append("Топик: ").append(message.topic()).append(" / ");
        sb.append("Партиция: ").append(message.partition()).append(" / ");
        sb.append("Оффсет: ").append(message.offset()).append(" / ");
        sb.append("Дата: ").append(message.timestamp()).append(" / ");
        sb.append("Сообщение: ").append(message.value()).append(" / ");

        Cat cat = message.value();
        cat.setName(cat.getName() + " " + message.offset());

        catService.strokingCat(cat);
    }
}
