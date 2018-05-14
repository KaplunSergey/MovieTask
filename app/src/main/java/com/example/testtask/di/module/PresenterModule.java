package com.example.testtask.di.module;

import android.support.annotation.NonNull;

import com.example.testtask.data.base.Repository;
import com.example.testtask.ui.detailView.presenter.MovieDetailPresenter;
import com.example.testtask.ui.detailView.presenter.MovieDetailPresenterImpl;
import com.example.testtask.ui.mainView.presenter.MoviesPresenter;
import com.example.testtask.ui.mainView.presenter.MoviesPresenterImpl;

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
    MoviesPresenter provideMoviesPresenter(Repository repository) {
        return new MoviesPresenterImpl(repository);
    }
}
