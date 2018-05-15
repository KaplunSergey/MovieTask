package com.example.testtask.data.network.callback;

import com.example.testtask.data.network.movie.MovieNet;

import java.util.List;

public interface MovieNetDownloadListener {

    void moviesDownloaded(List<MovieNet> movies);

    void loadingError(Throwable t);

}
