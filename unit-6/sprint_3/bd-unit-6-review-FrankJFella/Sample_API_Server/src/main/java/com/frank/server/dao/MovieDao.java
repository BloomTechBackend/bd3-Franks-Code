package com.frank.server.dao;

import com.frank.server.model.Movie;
import com.frank.server.datastore.MovieDataStore;

import java.util.List;

public class MovieDao {

    static final MovieDataStore movieDataBase;

    static {
        movieDataBase = new MovieDataStore();
    }

    public List<Movie> getAllMovies() {
        return movieDataBase.getAllMovies();
    }

    public Movie findMovie(int movieId) throws InterruptedException {
        return movieDataBase.findMovie(movieId);
    }
} // End of MovieDao class

