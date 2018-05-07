package com.example.testtask.ui.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.example.testtask.data.Repository;
import com.example.testtask.data.callback.MoviesDownloadListener;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.view.MoviesView;

import java.util.List;

public class MoviesPresenterImpl<V extends MoviesView> implements MoviesPresenter<V> {

    Repository repository;
    Context context;
    private V view;

    public MoviesPresenterImpl(Repository repository, Context context) {
        this.repository = repository;
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
    public void sortMovies() {

    }

    @Override
    public void updateMovies() {

    }

    @Override
    public void openMoviesDetails(int movieId) {

    }

    @Override
    public void loadMovies() {
        if (isOnline()) {
            if (repository.moviesDownloaded()) {
                view.showMovies(repository.getMovies());
            } else {
                view.startProgress();
                repository.downloadMovies(moviesDownloadListener);
            }
        } else {
            if (repository.moviesDownloaded()) {
                view.showMovies(repository.getMovies());
            } else {
                view.showMessageNoInternet();
            }
        }
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }


    MoviesDownloadListener moviesDownloadListener = new MoviesDownloadListener() {
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

        }
    };
}
