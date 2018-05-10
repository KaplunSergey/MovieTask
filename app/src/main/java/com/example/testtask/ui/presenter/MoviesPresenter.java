package com.example.testtask.ui.presenter;

import com.example.testtask.ui.view.MoviesView;

public interface MoviesPresenter extends Presenter<MoviesView> {
    void sortMovies(MoviesView.Sort sort);
    void updateMovies();
    void selectedMovie(int movieId, int position);
    void viewIsReady();
    void changeMovieElement(int movieId);
}
