package com.example.testtask.ui.view;

import com.example.testtask.data.database.Movie;

import java.util.List;

public interface MoviesView {
    void showMovies(List<Movie> movies);
    void showMessageNoInternet();
    void startProgress();
    void stopProgress();
}
