package com.example.testtask.ui.mainView.view;

import com.example.testtask.data.base.Movie;
import com.example.testtask.base.BaseView;

import java.util.List;

public interface MoviesView extends BaseView {
    /**
     * use empty lines to separate the methods better to look for them
     */
    void showMovies(List<Movie> movies);

    void startProgress();

    void stopProgress();

    void openMovie(int movieId);

    void updateMovieElement(Movie model);

    void sortMoviesRatingUp();

    void sortMoviesRatingDown();

    void sortMoviesYearUp();

    void sortMoviesYearDown();
}
