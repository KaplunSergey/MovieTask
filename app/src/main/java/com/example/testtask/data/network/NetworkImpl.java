package com.example.testtask.data.network;

import android.support.annotation.NonNull;

import com.example.testtask.data.network.callback.MovieDownloadListener;
import com.example.testtask.data.network.movie.MovieApi;
import com.example.testtask.data.network.movie.MovieNet;

import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkImpl implements Network {
    /**
     * Better to have Map<RequestId, Call>
     *
     * and cancel call by RequestId
     */
    private final MovieApi movieApi;
    private final LinkedHashMap<ReqId, Call> calls = new LinkedHashMap<>();

    public NetworkImpl(Retrofit retrofit) {
        movieApi = retrofit.create(MovieApi.class);
    }

    @Override
    public void downloadMovies(final MovieDownloadListener movieDownloadListener) {
        close(ReqId.GET_MOVIES);
        /**
         * better to create new call in method
         * not in constructor and try to clone it
         */
        final Call<List<MovieNet>> call = movieApi.getMovies();
        calls.put(ReqId.GET_MOVIES, call);

        call.enqueue(new Callback<List<MovieNet>>() {
            @Override
            public void onResponse(@NonNull Call<List<MovieNet>> call, @NonNull Response<List<MovieNet>> response) {
                List<MovieNet> movies = response.body();
                movieDownloadListener.moviesDownloaded(movies);
                calls.remove(ReqId.GET_MOVIES);
            }

            @Override
            public void onFailure(@NonNull Call<List<MovieNet>> call, @NonNull Throwable t) {
                movieDownloadListener.loadingError(t);
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
