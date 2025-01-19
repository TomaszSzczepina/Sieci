package com.example.jokeapi.controller;

import com.example.jokeapi.service.JokeService;
import com.example.jokeapi.model.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JokeController {

    private final JokeService jokeService;

    @Autowired
    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Wyświetl losowy żart
    @GetMapping("/random-joke")
    public String getRandomJoke(Model model) {
        String randomJoke = jokeService.getRandomJoke();
        model.addAttribute("joke", randomJoke);  // Przekazanie żartu do widoku
        return "random-joke";  // Nazwa szablonu HTML
    }

    // Zapisz żart do bazy danych
    @PostMapping("/save-joke")
    public String saveJoke(@RequestParam String jokeContent, Model model) {
        jokeService.saveJoke(jokeContent);
        model.addAttribute("message", "Żart został zapisany!");  // Komunikat dla użytkownika
        return "add-joke";  // Przekierowanie do strony z formularzem
    }

    // Strona z formularzem do dodawania żartów
    @GetMapping("/add-joke")
    public String addJokePage() {
        return "add-joke";
    }

    // Wyświetl wszystkie zapisane żarty z bazy danych
    @GetMapping("/jokes")
    public String getAllJokes(Model model) {
        Iterable<Joke> jokes = jokeService.getAllJokes();
        model.addAttribute("jokes", jokes);
        return "all-jokes";
    }

    // Edytowanie żartu
    @GetMapping("/edit-joke/{id}")
    public String editJoke(@PathVariable Long id, Model model) {
        Joke joke = jokeService.getJokeById(id);
        if (joke != null) {
            model.addAttribute("joke", joke);
            return "edit-joke";
        }
        return "redirect:/jokes";
    }

    // Zaktualizowanie żartu
    @PostMapping("/update-joke/{id}")
    public String updateJoke(@PathVariable Long id, @RequestParam("jokeContent") String jokeContent) {
        jokeService.updateJoke(id, jokeContent);
        return "redirect:/jokes";
    }

    // Usuwanie żartu
    @GetMapping("/delete-joke/{id}")
    public String deleteJoke(@PathVariable Long id) {
        jokeService.deleteJoke(id);
        return "redirect:/jokes";
    }
}

