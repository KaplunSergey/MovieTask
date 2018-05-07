package com.example.testtask.ui.presenter;

import android.support.annotation.NonNull;

import com.example.testtask.ui.view.MovieDetailView;

public interface MovieDetailPresenter<V extends MovieDetailView> {
    void attachView(@NonNull V view);
    void detachView();
    void bookmarkMovie();
    void onBack();
}
