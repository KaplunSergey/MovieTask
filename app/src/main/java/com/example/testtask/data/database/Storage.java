package com.example.testtask.data.database;

import com.example.testtask.data.database.movie.MovieDb;

import java.util.List;

public interface Storage {
    void addMovies(List<MovieDb> movies);
    MovieDb getMovie(int id);
    List<MovieDb> getMovies();
    void updateMoviesBookmark(int id, boolean bookmark);
    boolean moviesDownloaded();
    void deleteMovies();
    void close();
}
