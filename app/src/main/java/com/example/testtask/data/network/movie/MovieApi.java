package com.example.testtask.data.network.movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("movies.json")
    Call<List<MovieNet>> getMovies();
}
