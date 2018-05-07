package com.example.testtask.ui.presenter;

import android.support.annotation.NonNull;

import com.example.testtask.data.Repository;
import com.example.testtask.ui.view.MovieDetailView;

public class MovieDetailPresenterImpl<V extends MovieDetailView> implements MovieDetailPresenter<V> {

    Repository repository;

    public MovieDetailPresenterImpl(Repository repository) {
        this.repository = repository;
    }

    private V movieDetailView;

    @Override
    public void attachView(@NonNull V view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void bookmarkMovie() {

    }

    @Override
    public void onBack() {

    }
}
