package com.example.testtask.ui.presenter;

import android.support.annotation.NonNull;

import com.example.testtask.ui.view.MoviesView;

public interface MoviesPresenter {
    void addView(@NonNull MoviesView movieView);
}
