package com.example.testtask.data;

import com.example.testtask.data.callback.MoviesDownloadListener;
import com.example.testtask.data.database.Movie;

import java.util.List;

public interface Repository {
    void downloadMovies(MoviesDownloadListener moviesDownloadListener);
    List<Movie> getMovies();
    Movie getMovie(int id);
    void saveMovies(List<Movie> movies);
    void updateMovieBookmark(int id, boolean bookmark);
    boolean moviesDownloaded();
    void deleteMovies();
}
