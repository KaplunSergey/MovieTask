package com.example.testtask.callback;

import com.example.testtask.data.database.Movie;

import java.util.List;

public interface MoviesDownloadListener {
    void moviesDownloaded(List<Movie> movies);
    void loadingError(Throwable t);
}
