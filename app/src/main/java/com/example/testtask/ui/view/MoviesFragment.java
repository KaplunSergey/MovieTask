package com.example.testtask.ui.view;

import android.app.Fragment;

import com.example.testtask.App;
import com.example.testtask.ui.presenter.MoviesPresenter;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MoviesView {

    @Inject
    MoviesPresenter moviesPresenter;

    public MoviesFragment() {
        App.getPresenterComponent().inject(this);
    }
}
