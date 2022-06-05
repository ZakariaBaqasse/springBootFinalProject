package com.example.finalproject.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String movieTitle;
    private String moviePoster;
    private String movieDuration;
    private String movieRating;

    public Movie(Long movieId, String movieTitle, String moviePoster, String movieDuration, String movieRating) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.moviePoster = moviePoster;
        this.movieDuration = movieDuration;
        this.movieRating = movieRating;
    }

    public Movie(String movieTitle, String moviePoster, String movieDuration, String movieRating) {
        this.movieTitle = movieTitle;
        this.moviePoster = moviePoster;
        this.movieDuration = movieDuration;
        this.movieRating = movieRating;
    }

    public Movie() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }
}
