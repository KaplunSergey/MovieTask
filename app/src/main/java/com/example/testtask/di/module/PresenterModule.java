package com.example.testtask.di.module;

import android.support.annotation.NonNull;

import com.example.testtask.ui.presenter.MovieDetailPresenter;
import com.example.testtask.ui.presenter.MovieDetailPresenterImpl;
import com.example.testtask.ui.presenter.MoviesPresenter;
import com.example.testtask.ui.presenter.MoviesPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    @NonNull
    MovieDetailPresenter provideMovieDetailPresenter() {
        return new MovieDetailPresenterImpl();
    }

    @Provides
    @NonNull
    MoviesPresenter provideMoviesPresenter() {
        return new MoviesPresenterImpl();
    }
}
