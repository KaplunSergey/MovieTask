package com.example.testtask.data.network;

import android.support.annotation.NonNull;

import com.example.testtask.data.network.callback.MovieDownloadListener;
import com.example.testtask.data.network.movie.MovieApi;
import com.example.testtask.data.network.movie.MovieNet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkImpl implements Network {

    private Call<List<MovieNet>> getMoviesCall;

    public NetworkImpl(Retrofit retrofit) {
        MovieApi movieApi = retrofit.create(MovieApi.class);
        getMoviesCall = movieApi.getMovies();
    }

    @Override
    public void downloadMovies(final MovieDownloadListener movieDownloadListener) {
        if (getMoviesCall.isExecuted()) {
            getMoviesCall.cancel();
        }

        if (getMoviesCall.isCanceled()) {
            getMoviesCall = getMoviesCall.clone();
        }

        getMoviesCall.enqueue(new Callback<List<MovieNet>>() {
            @Override
            public void onResponse(@NonNull Call<List<MovieNet>> call, @NonNull Response<List<MovieNet>> response) {
                List<MovieNet> movies = response.body();
                movieDownloadListener.moviesDownloaded(movies);
            }

            @Override
            public void onFailure(@NonNull Call<List<MovieNet>> call, @NonNull Throwable t) {
                movieDownloadListener.loadingError(t);
            }
        });
    }

    @Override
    public void close() {
        if (getMoviesCall != null) {
            getMoviesCall.cancel();
        }
    }
}
