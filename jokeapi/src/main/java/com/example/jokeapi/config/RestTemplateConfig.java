package com.example.jokeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // Bean RestTemplate, który będzie używany w całej aplikacji
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
