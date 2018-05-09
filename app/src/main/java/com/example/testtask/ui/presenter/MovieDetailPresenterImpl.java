package com.example.testtask.ui.presenter;

import android.support.annotation.NonNull;

import com.example.testtask.data.Repository;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.view.MovieDetailView;

public class MovieDetailPresenterImpl<V extends MovieDetailView> implements MovieDetailPresenter<V> {

    private Repository repository;
    private V view;
    private Movie movie;


    public MovieDetailPresenterImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void attachView(@NonNull V view) {
        this.view = view;
    }

    @Override
    public void setMovieId(int movieId) {
        movie = repository.getMovie(movieId);
        view.showMovie(movie);
    }

    @Override
    public void bookmarkButtonClicked() {
        movie.setBookmark(!movie.isBookmark());
        view.updateBookmarkButton(movie.isBookmark());
    }

    @Override
    public void onBack() {
        repository.updateMovieBookmark(movie.getId(), movie.isBookmark());
    }

    @Override
    public void detachView() {
        view = null;
    }
}
