package com.example.finalproject.controllers;


import com.example.finalproject.entity.Movie;
import com.example.finalproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/getMovies")
    public List<Movie> getListOfMovies(){
        return movieService.getAllMovies();
    }
    @PostMapping("/insertMovie")
    public void saveMovie(@RequestBody Movie movie){
        movieService.saveMovie(movie);
    }

}
