package com.example.testtask.data.network;

import com.example.testtask.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkImpl implements Network {

    @Inject
    Retrofit retrofit;

    public NetworkImpl() {
        App.getNetworkComponent().inject(this);
    }

    @Override
    public List<FilmModel> downloadFilmsModels() {

        FilmApi moviesApi = retrofit.create(FilmApi.class);
        final List<FilmModel> films = new ArrayList<>();
        Call<List<FilmModel>> call = moviesApi.getMovies();

        call.enqueue(new Callback<List<FilmModel>>() {
            @Override
            public void onResponse(Call<List<FilmModel>> call, Response<List<FilmModel>> response) {
                films.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<FilmModel>> call, Throwable t) {

            }
        });

        return films;
    }
}
