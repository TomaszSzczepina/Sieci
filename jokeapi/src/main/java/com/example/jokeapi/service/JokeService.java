package com.example.jokeapi.service;

import com.example.jokeapi.model.Joke;
import com.example.jokeapi.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class JokeService {

    private final JokeRepository jokeRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public JokeService(JokeRepository jokeRepository, RestTemplate restTemplate) {
        this.jokeRepository = jokeRepository;
        this.restTemplate = restTemplate;
    }

    // Metoda pobierająca wszystkie żarty
    public Iterable<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    // Metoda zapisująca żart do bazy danych
    public void saveJoke(String content) {
        Joke joke = new Joke();
        joke.setContent(content);
        jokeRepository.save(joke);
    }

    // Metoda pobierająca żart
    public Joke getJokeById(Long id) {
        return jokeRepository.findById(id).orElse(null);
    }

    // Metoda do edytowania żartu
    public void updateJoke(Long id, String content) {
        Joke joke = jokeRepository.findById(id).orElse(null);
        if (joke != null) {
            joke.setContent(content);
            jokeRepository.save(joke);
        } else {
            throw new IllegalArgumentException("Nie znaleziono żartu o ID " + id);
        }
    }

    // Pobranie losowego żartu z API
    public String getRandomJoke() {

        String apiUrl = "https://v2.jokeapi.dev/joke/Any?type=single";

        JokeApiResponse response = restTemplate.getForObject(apiUrl, JokeApiResponse.class);

        if (response != null && response.getJoke() != null && !response.getJoke().isEmpty()) {
            return response.getJoke();
        }
        return "Brak żartu w odpowiedzi API.";
    }

    // Usuwanie żartu po ID
    public void deleteJoke(Long id) {
        jokeRepository.deleteById(id);
    }

}





