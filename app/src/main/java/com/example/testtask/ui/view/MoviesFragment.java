package com.example.testtask.ui.view;

import android.app.Fragment;

import com.example.testtask.App;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.presenter.MoviesPresenter;

import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MoviesView {

    @Inject
    MoviesPresenter moviesPresenter;

    public MoviesFragment() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void showMovies(List<Movie> movies) {

    }

    @Override
    public void showMessageNoInternet() {

    }

    @Override
    public void startProgress() {

    }

    @Override
    public void stopProgress() {

    }
}
