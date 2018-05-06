package com.example.testtask.mvp;

import android.support.annotation.NonNull;

public class PresenterImpl implements Presenter {

    private MovieView movieView;

    @Override
    public void addView(@NonNull MovieView movieView) {
        this.movieView = movieView;
    }
}
