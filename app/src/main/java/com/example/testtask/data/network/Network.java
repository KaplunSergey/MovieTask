package com.example.testtask.data.network;

import com.example.testtask.data.network.callback.MovieNetDownloadListener;

public interface Network {

    void downloadMovies(MovieNetDownloadListener movieNetDownloadListener);

    void close(ReqId id);

    void close();

    enum ReqId {
        GET_MOVIES
    }
}
