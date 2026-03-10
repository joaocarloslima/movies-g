package br.com.fiap.movies.services;

import br.com.fiap.movies.models.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MovieService {

    private List<Movie> repository = new ArrayList<>();

    public List<Movie> getAllMovies(){
        return repository;
    }

    public Movie addMovie(Movie movie){
        movie.setId(Math.abs( new Random().nextLong() ));
        repository.add(movie);
        return movie;
    }

    public Optional<Movie> getMovieById(Long id){
        return findMovieById(id);
    }

    public void deleteMovie(Long id) {
        repository.remove(findMovieById(id).get());
    }

    public Movie updateMovie(Long id, Movie movie) {
        repository.remove(findMovieById(id).get());
        movie.setId(id);
        repository.add(movie);

        return movie;
    }

    private Optional<Movie> findMovieById(Long id) {
        var optionalMovie = repository.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();

        if(optionalMovie.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme com id " + id + " não encontrado");
        }
        return optionalMovie;
    }
}
