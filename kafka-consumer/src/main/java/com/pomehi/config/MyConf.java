package com.pomehi.config;

import com.pomehi.dto.Cat;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.List;
import java.util.Map;

@Configuration
public class MyConf {

    public JsonDeserializer getJsonDeserializer() {
        JsonDeserializer jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");
        return jsonDeserializer;
    }

    public Map<String, Object> getPropertyFromKafka() {
        KafkaProperties prop = new KafkaProperties();
        KafkaProperties.Consumer consumer = prop.getConsumer();
        consumer.setBootstrapServers(List.of("localhost:9092"));
        consumer.setValueDeserializer(JsonDeserializer.class);
        consumer.setGroupId("1");
        return consumer.buildProperties();
    }

    public ConsumerFactory<String, Cat> getConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getPropertyFromKafka(), new StringDeserializer(), getJsonDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Cat> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Cat> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(getConsumerFactory());
        return factory;
    }
}
