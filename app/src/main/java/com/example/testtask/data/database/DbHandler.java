package com.example.testtask.data.database;

import java.util.List;

public interface DbHandler {
    void addMovies(List<Movie> movies);
    Movie getMovie(long id);
    List<Movie> getMovies();
    boolean moviesDownloaded();
    void updateMovieBookmark(long id, boolean bookmark);
    void deleteMovies();
}
