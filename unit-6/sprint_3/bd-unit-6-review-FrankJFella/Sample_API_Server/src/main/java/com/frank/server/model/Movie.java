package com.frank.server.model;

import java.time.LocalDate;
import java.util.Objects;

// Hold the information we want for one Movie returned from teh API
// variable names must match the JSON attribute names sent from the API
// Only need to define variables for the attributes wanted in the application
public class Movie {
    private int        movieNumber;   // Generated whenteh object is instantiated for the class
    private int        id;            // populated from JSON returned from the API
    private Boolean    adult;         // populated from JSON returned from the API
    private String     title;         // populated from JSON returned from the API
    private LocalDate  release_date;  // populated from JSON returned from the API
    private String     overview;      // populated from JSON returned from the API

    public Movie() {}

    public int getMovieNumber() {
        return movieNumber;
    }

    public void setMovieNumber(int movieNumber) {
        this.movieNumber = movieNumber;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getMovieNumber() == movie.getMovieNumber() && getId() == movie.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieNumber(), getId());
    }
} // End of Movie class
