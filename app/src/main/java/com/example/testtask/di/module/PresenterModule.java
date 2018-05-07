package com.example.testtask.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.testtask.data.Repository;
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
    MovieDetailPresenter provideMovieDetailPresenter(Repository repository) {
        return new MovieDetailPresenterImpl(repository);
    }

    @Provides
    @NonNull
    MoviesPresenter provideMoviesPresenter(Repository repository, Context context) {
        return new MoviesPresenterImpl(repository, context);
    }
}
