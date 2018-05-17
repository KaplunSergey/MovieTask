package com.example.testtask.data.database.movie;

import java.util.List;

public interface MovieDAO {

    void addMovies(List<MovieDb> movies);

    MovieDb getMovie(long id);

    List<MovieDb> getMovies();

    boolean moviesDownloaded();

    void updateMovieBookmark(long id, boolean bookmark);

    void deleteMovies();

    void closeDb();
}
