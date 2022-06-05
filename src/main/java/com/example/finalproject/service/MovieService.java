package com.example.finalproject.service;


import com.example.finalproject.dao.MovieDao;
import com.example.finalproject.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieDao movieDao;

    public List<Movie> getAllMovies(){
        return (List<Movie>) movieDao.findAll();
    }

    public Movie saveMovie(Movie movie){
        return movieDao.save(movie);
    }


}
