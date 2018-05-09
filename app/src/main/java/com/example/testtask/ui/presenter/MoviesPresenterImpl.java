package com.example.testtask.ui.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.callback.MoviesDownloadListener;
import com.example.testtask.data.Repository;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.view.MoviesView;

import java.util.Collections;
import java.util.List;

public class MoviesPresenterImpl<V extends MoviesView> implements MoviesPresenter<V> {

    private Repository repository;
    private Context context;
    private V view;

    public MoviesPresenterImpl(Repository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }

    @Override
    public void attachView(@NonNull V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void sortMovies(V.Sort sort) {

        List<Movie> movies = repository.getMovies();

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
    public void updateMovies() {
        view.startProgress();
        if (isOnline()) {
            repository.downloadMovies(moviesDownloadListener);
        } else {
            view.stopProgress();
            view.showMessage("Can not download data, check the connection to the Internet");
        }
    }

    @Override
    public void selectedMovie(int movieId) {
        view.openMovieDetailFragment(repository.getMovie(movieId).getId());
    }

    @Override
    public void viewIsReady() {
        view.startProgress();
        if (isOnline()) {
            if (repository.moviesDownloaded()) {
                view.showMovies(repository.getMovies());
                view.stopProgress();
            } else {
                repository.downloadMovies(moviesDownloadListener);
            }
        } else {
            if (repository.moviesDownloaded()) {
                view.showMovies(repository.getMovies());
                view.stopProgress();
            } else {
                view.showMessage("Can not download data, check the connection to the Internet");
                view.stopProgress();
            }
        }
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }


    private MoviesDownloadListener moviesDownloadListener = new MoviesDownloadListener() {
        @Override
        public void moviesDownloaded(List<Movie> movies) {
            if (repository.moviesDownloaded()) {
                repository.deleteMovies();
            }
            repository.saveMovies(movies);
            List<Movie> dbMovies = repository.getMovies();
            view.showMovies(dbMovies);
            view.stopProgress();
        }

        @Override
        public void loadingError(Throwable t) {
            view.showMessage(t.getMessage());
        }
    };
}
