package com.example.testtask.ui.mainView.view;

import com.example.testtask.ui.base.BaseView;
import com.example.testtask.data.base.model.Movie;

import java.util.List;

public interface MoviesView extends BaseView {

    void showMovies(List<Movie> movies);

    void startProgress();

    void stopProgress();

    void openMovie(int movieId);

    void updateMovieElement(Movie model);

}
