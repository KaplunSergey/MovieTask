package com.example.testtask.ui.mainView.presenter;

import com.example.testtask.base.BasePresenter;
import com.example.testtask.ui.mainView.view.MoviesView;

public interface MoviesPresenter extends BasePresenter<MoviesView> {
    void sortMoviesRatingUp();
    void sortMoviesRatingDown();
    void sortMoviesYearUp();
    void sortMoviesYearDown();
    void updateMovies();
    void selectedMovie(int movieId);
    void viewIsReady();
    void changeMovieElement(int movieId);
}
