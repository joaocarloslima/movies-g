package br.com.fiap.movies.controllers;

import br.com.fiap.movies.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
public class MovieController {

    record HealthStatus(String status, String message){}

    @GetMapping("/")
    public HealthStatus healthCheck() {
        return new HealthStatus("OK", "API is running");
    }

    @GetMapping("/movies")
    public List<Movie> listAll(){
        return List.of( new Movie(
                "Titanic",
                5,
                "Todos morrem no final",
                LocalDate.of(1997, 12, 01),
                "2h30",
                "Romance"
        ));
    }

    @PostMapping("/movies")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie movie){ //binding
        log.info("Cadastrando filme..." + movie);
        return movie;
    }

}
