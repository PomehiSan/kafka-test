package com.pomehi.kafkaproducer.config;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
public class MyConf {

    @Bean
    public Map<String, Object> getPropertyFromKafka() {
        KafkaProperties prop = new KafkaProperties();
        KafkaProperties.Producer producer = prop.getProducer();
        producer.setBootstrapServers(List.of("localhost:9092"));
        return producer.buildProperties();
    }

    @Bean
    public ProducerFactory<String, String> getProducerFactory() {
        return new DefaultKafkaProducerFactory<>(getPropertyFromKafka());
    }

    @Bean
    public KafkaTemplate<String, String> getKafka() {
        return new KafkaTemplate<>(getProducerFactory());
    }
}
