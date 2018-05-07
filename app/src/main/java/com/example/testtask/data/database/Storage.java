package com.example.testtask.data.database;

import java.util.List;

public interface Storage {
    void addMovies(List<Movie> movies);
    Movie getMovie(int id);
    List<Movie> getMovies();
    void updateMoviesBookmark(int id, boolean bookmark);
    boolean moviesDownloaded();
    void deleteMovies();
}
