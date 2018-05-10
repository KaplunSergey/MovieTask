package com.example.testtask.ui.presenter;

import com.example.testtask.data.Repository;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.view.MovieDetailView;

public class MovieDetailPresenterImpl implements MovieDetailPresenter{

    private Repository repository;
    private MovieDetailView view;
    private Movie movie;


    public MovieDetailPresenterImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setMovieId(int movieId) {
        movie = repository.getMovie(movieId);
        view.showMovie(movie);
    }

    @Override
    public void attachView(MovieDetailView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void bookmarkButtonClicked() {
        movie.setBookmark(!movie.isBookmark());
        view.updateBookmarkButton(movie.isBookmark());
    }

    @Override
    public void onBackPressed() {
        repository.updateMovieBookmark(movie.getId(), movie.isBookmark());
    }
}
