package com.example.testtask.ui.presenter;

import android.support.annotation.NonNull;

import com.example.testtask.App;
import com.example.testtask.data.Repository;
import com.example.testtask.ui.view.MovieDetailView;

import javax.inject.Inject;

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    @Inject
    Repository repository;

    private MovieDetailView movieDetailView;

    public MovieDetailPresenterImpl() {
        App.getRepositoryComponent().inject(this);
    }

    @Override
    public void addView(@NonNull MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
    }
}
