package com.example.testtask.ui.mainView.presenter;

import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.data.base.callback.MovieDownloadListener;
import com.example.testtask.data.base.callback.MoviesDownloadListener;
import com.example.testtask.data.base.Movie;
import com.example.testtask.data.base.Repository;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.ui.mainView.view.MoviesView;

import java.util.Collections;
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
        sortMovies(Sort.RATING_UP);
    }

    @Override
    public void sortMoviesRatingDown() {
        sortMovies(Sort.RATING_DOWN);
    }

    @Override
    public void sortMoviesYearUp() {
        sortMovies(Sort.YEAR_UP);
    }

    @Override
    public void sortMoviesYearDown() {
        sortMovies(Sort.YEAR_DOWN);
    }

    @Override
    public void updateMovies() {
        view.startProgress();
        repository.downloadMovies(moviesDownloadListener);
    }

    @Override
    public void selectedMovie(final int movieId) {
        repository.getMovie(movieId, new MovieDownloadListener() {
            @Override
            public void movieDownloaded(Movie movie) {
                view.openMovie(movie.getId());
            }

            @Override
            public void error(RepositoryException ex) {
                view.showMessage(ex.getMessage());
            }
        });
    }

    @Override
    public void viewIsReady() {
        view.startProgress();
        repository.getMovies(moviesDownloadListener);
    }

    @Override
    public void changeMovieElement(int movieId) {

        /**
         * same issue as with selectedMovie
         */

        repository.getMovie(movieId, new MovieDownloadListener() {
            @Override
            public void movieDownloaded(Movie movie) {
                view.updateMovieElement(movie);
            }

            @Override
            public void error(RepositoryException ex) {
                view.showMessage(ex.getMessage());
            }
        });
    }

    private void sortMovies(final Sort sort) {

        repository.getMovies(new MoviesDownloadListener() {
            @Override
            public void moviesDownloaded(List<Movie> movies) {
                switch (sort) {
                    case RATING_UP:
                        Collections.sort(movies, Collections.reverseOrder(MovieUtils.COMPARE_BY_RATING));
                        break;
                    case YEAR_UP:
                        Collections.sort(movies, Collections.reverseOrder(MovieUtils.COMPARE_BY_YEAR));
                        break;
                    case RATING_DOWN:
                        Collections.sort(movies, MovieUtils.COMPARE_BY_RATING);
                        break;
                    case YEAR_DOWN:
                        Collections.sort(movies, MovieUtils.COMPARE_BY_YEAR);
                        break;
                }

                view.showMovies(movies);
            }

            @Override
            public void error(RepositoryException ex) {
                view.showMessage(ex.getMessage());
            }
        });

    }

    /**
     * Of course it is better to have RxJava and CompositeDisposable to unSubscribe from data
     * <p>
     * in Detach for example
     * <p>
     * instead of check for null on view
     */

    private MoviesDownloadListener moviesDownloadListener = new MoviesDownloadListener() {
        @Override
        public void moviesDownloaded(List<Movie> movies) {
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

    private enum Sort {
        RATING_UP,
        RATING_DOWN,
        YEAR_UP,
        YEAR_DOWN
    }
}
