package com.example.testtask.ui.presenter;

import android.support.annotation.NonNull;

import com.example.testtask.ui.view.MoviesView;

public interface MoviesPresenter<V extends MoviesView> {
    void attachView(@NonNull V view);
    void detachView();
    void sortMovies();
    void updateMovies();
    void openMoviesDetails(int movieId);
    void loadMovies();
}
