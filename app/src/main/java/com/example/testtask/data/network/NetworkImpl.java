package com.example.testtask.data.network;

import android.support.annotation.NonNull;

import com.example.testtask.data.network.callback.MovieNetDownloadListener;
import com.example.testtask.data.network.movie.MovieApi;
import com.example.testtask.data.network.movie.MovieNet;

import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkImpl implements Network {

    private final MovieApi movieApi;
    private final LinkedHashMap<ReqId, Call> calls = new LinkedHashMap<>();

    public NetworkImpl(Retrofit retrofit) {
        movieApi = retrofit.create(MovieApi.class);
    }

    @Override
    public void downloadMovies(final MovieNetDownloadListener movieNetDownloadListener) {
        close(ReqId.GET_MOVIES);

        final Call<List<MovieNet>> call = movieApi.getMovies();
        calls.put(ReqId.GET_MOVIES, call);

        call.enqueue(new Callback<List<MovieNet>>() {
            @Override
            public void onResponse(@NonNull Call<List<MovieNet>> call, @NonNull Response<List<MovieNet>> response) {
                List<MovieNet> movies = response.body();
                movieNetDownloadListener.moviesDownloaded(movies);
                calls.remove(ReqId.GET_MOVIES);
            }

            @Override
            public void onFailure(@NonNull Call<List<MovieNet>> call, @NonNull Throwable t) {
                movieNetDownloadListener.loadingError(t);
                calls.remove(ReqId.GET_MOVIES);
            }
        });
    }

    @Override
    public void close(ReqId id) {
        if (calls.containsKey(id)) calls.get(id).cancel();
    }

    @Override
    public void close() {
        for (Call call: calls.values()) call.cancel();
    }
}
