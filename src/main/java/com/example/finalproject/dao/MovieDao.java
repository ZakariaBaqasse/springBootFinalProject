package com.example.finalproject.dao;

import com.example.finalproject.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDao extends CrudRepository<Movie, Long> {
}
