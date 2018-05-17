package com.example.testtask.ui.mainView.presenter;

import com.example.testtask.data.base.callback.MovieListener;
import com.example.testtask.data.base.model.Movie;
import com.example.testtask.data.base.Repository;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.ui.mainView.view.MoviesView;

import java.util.List;

public class MoviesPresenterImpl implements MoviesPresenter {

    private Repository repository;
    private MoviesView view;

    public MoviesPresenterImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void attachView(MoviesView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        repository.close();
        view = null;
    }

    @Override
    public void sortMoviesRatingUp() {
        view.sortMoviesRatingUp();
    }

    @Override
    public void sortMoviesRatingDown() {
        view.sortMoviesRatingDown();
    }

    @Override
    public void sortMoviesYearUp() {
        view.sortMoviesYearUp();
    }

    @Override
    public void sortMoviesYearDown() {
        view.sortMoviesYearDown();
    }

    @Override
    public void updateMovies() {
        view.startProgress();
        repository.downloadMovies(movieListener);
    }

    @Override
    public void selectedMovie(int movieId) {
        Movie movie;
        try {
            movie = repository.getMovie(movieId);
        } catch (RepositoryException e) {
            view.showMessage(e.getMessage());
            return;
        }
        view.openMovie(movie.getId());
    }

    @Override
    public void viewIsReady() {
        view.startProgress();
        repository.getMovies(movieListener);
    }

    @Override
    public void changeMovieElement(int movieId) {
        Movie movie;
        try {
            movie = repository.getMovie(movieId);
        } catch (RepositoryException e) {
            view.showMessage(e.getMessage());
            return;
        }
        view.updateMovieElement(movie);
    }


    private MovieListener movieListener = new MovieListener() {
        @Override
        public void getMovies(List<Movie> movies) {
            if (view == null) {
                return;
            }
            view.showMovies(movies);
            view.stopProgress();
        }

        @Override
        public void error(RepositoryException ex) {
            if (view == null) {
                return;
            }
            view.showMessage(ex.getMessage());
            view.stopProgress();
        }
    };
}
