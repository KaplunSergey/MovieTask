package com.example.testtask.data.network;

import com.example.testtask.data.network.callback.MovieDownloadListener;

public interface Network {
    void downloadMovies(MovieDownloadListener movieDownloadListener);
    void close(ReqId id);
    void close();

    enum ReqId {
        GET_MOVIES
    }
}
