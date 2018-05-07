package com.example.testtask.data.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET
    Call<List<MovieModel>> getMovies();

}
