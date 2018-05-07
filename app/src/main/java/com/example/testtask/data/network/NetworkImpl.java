package com.example.testtask.data.network;

import com.example.testtask.data.callback.MoviesDownloadListener;
import com.example.testtask.data.database.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkImpl implements Network {

    Retrofit retrofit;

    public NetworkImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void downloadMovies(final MoviesDownloadListener moviesDownloadListener) {

        MovieApi moviesApi = retrofit.create(MovieApi.class);
        Call<List<MovieModel>> call = moviesApi.getMovies();

        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {

                List<Movie> movies = new ArrayList<>();

                for (MovieModel model : response.body()) {
                    Movie movie = new Movie();
                    movie.setGenre(model.getGenre());
                    movie.setImageUrl(model.getImage());
                    movie.setRating(model.getRating());
                    movie.setTitle(model.getTitle());
                    movie.setYear(model.getReleaseYear());
                    movies.add(movie);
                }

                moviesDownloadListener.moviesDownloaded(movies);
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                moviesDownloadListener.loadingError(t);
            }
        });
    }
}
