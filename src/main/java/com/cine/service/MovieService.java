package com.cine.service;

import com.cine.model.Movie;
import com.cine.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {
        if (movie.getSchedules() == null || movie.getSchedules().isEmpty()) {
            String movieSchedules = "10:00,13:00,16:00,19:00,22:00";
            movie.setSchedules(movieSchedules);
        }
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovie(int id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new RuntimeException("La película con ID " + id + " no existe.");
        }
    }

    public Movie updateMovie(int id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Película no encontrada"));

        movie.setTitle(movieDetails.getTitle());
        movie.setGenre(movieDetails.getGenre());
        movie.setDuration(movieDetails.getDuration());
        movie.setClassification(movieDetails.getClassification());
        movie.setSchedules(movieDetails.getSchedules());

        return movieRepository.save(movie);
    }
}
