package com.pomehi.config;

import com.pomehi.dto.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public BlockingDeque<Cat> cats() {
        return new LinkedBlockingDeque<>();
    }

    @Bean
    public ExecutorService executorServices() {
        return Executors.newFixedThreadPool(4);
    }
}
