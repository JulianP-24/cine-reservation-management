package com.cine.controller;

import com.cine.model.Movie;
import com.cine.service.MovieService;
import com.cine.util.AppSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/find-movies")
    public ResponseEntity<?> getALLMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-movies")
    public ResponseEntity<?> newMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.OK);
    }

    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id) {
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-movie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        return ResponseEntity.ok(updatedMovie);
    }
}
