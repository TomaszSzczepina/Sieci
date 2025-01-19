package com.example.jokeapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.example.jokeapi.service.JokeService;

@SpringBootApplication
public class JokeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokeApiApplication.class, args);
	}
}



