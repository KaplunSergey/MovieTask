package com.example.testtask.ui.view;

import com.example.testtask.data.database.Movie;

import java.util.List;

public interface MoviesView {
    void showMovies(List<Movie> movies);
    void showMessage(String message);
    void startProgress();
    void stopProgress();
    void openMovieDetailFragment(int movieId);

    enum Sort{
        RATING_UP,
        RATING_DOWN,
        YEAR_UP,
        YEAR_DOWN
    }
}
