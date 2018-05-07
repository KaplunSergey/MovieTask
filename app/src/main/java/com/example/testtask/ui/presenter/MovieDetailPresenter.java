package com.example.testtask.ui.presenter;

import android.support.annotation.NonNull;

import com.example.testtask.ui.view.MovieDetailView;

public interface MovieDetailPresenter {
    void addView(@NonNull MovieDetailView movieView);
}
