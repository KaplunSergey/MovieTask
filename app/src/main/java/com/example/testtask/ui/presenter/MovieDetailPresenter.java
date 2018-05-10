package com.example.testtask.ui.presenter;

import com.example.testtask.ui.view.MovieDetailView;

public interface MovieDetailPresenter extends Presenter<MovieDetailView> {
    void setMovieId(int movieId);
    void detachView();
    void bookmarkButtonClicked();
    void onBack();
}
