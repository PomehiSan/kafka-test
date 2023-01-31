package com.pomehi.config;

import com.pomehi.dto.Cat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.List;
import java.util.Map;

@Configuration
public class MyConf {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaIP;

    @Bean
    public Map<String, Object> getPropertyFromKafka() {
        KafkaProperties prop = new KafkaProperties();
        KafkaProperties.Producer producer = prop.getProducer();
        producer.setBootstrapServers(List.of(kafkaIP));
        producer.setValueSerializer(JsonSerializer.class);
        return producer.buildProperties();
    }

    @Bean
    public ProducerFactory<String, Cat> getProducerFactory() {
        return new DefaultKafkaProducerFactory<>(getPropertyFromKafka());
    }

    @Bean
    public KafkaTemplate<String, Cat> getKafka() {
        return new KafkaTemplate<>(getProducerFactory());
    }
}
