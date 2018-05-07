package com.example.testtask.ui.presenter;

import android.support.annotation.NonNull;

import com.example.testtask.App;
import com.example.testtask.data.Repository;
import com.example.testtask.ui.view.MoviesView;

import javax.inject.Inject;

public class MoviesPresenterImpl implements MoviesPresenter {

    @Inject
    Repository repository;

    private MoviesView movieView;

    public MoviesPresenterImpl() {
        App.getRepositoryComponent().inject(this);
    }

    @Override
    public void addView(@NonNull MoviesView movieView) {
        this.movieView = movieView;
    }
}
