package com.example.testtask.data.network;

import com.example.testtask.data.callback.MoviesDownloadListener;

public interface Network {

    void downloadMovies(MoviesDownloadListener moviesDownloadListener);
}
