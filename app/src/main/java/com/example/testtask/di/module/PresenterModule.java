package com.example.testtask.di.module;

import android.support.annotation.NonNull;

import com.example.testtask.data.base.Repository;
import com.example.testtask.ui.authorizationView.presenter.AuthorizationPresenter;
import com.example.testtask.ui.authorizationView.presenter.AuthorizationPresenterImpl;
import com.example.testtask.ui.detailView.presenter.MovieDetailPresenter;
import com.example.testtask.ui.detailView.presenter.MovieDetailPresenterImpl;
import com.example.testtask.ui.mainView.presenter.MoviesPresenter;
import com.example.testtask.ui.mainView.presenter.MoviesPresenterImpl;
import com.example.testtask.ui.registrationView.presenter.RegistrationPresenter;
import com.example.testtask.ui.registrationView.presenter.RegistrationPresenterImpl;

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

    @Provides
    @NonNull
    AuthorizationPresenter provideAuthorizationPresenter(Repository repository) {
        return new AuthorizationPresenterImpl(repository);
    }

    @Provides
    @NonNull
    RegistrationPresenter provideRegistrationPresenter(Repository repository) {
        return new RegistrationPresenterImpl(repository);
    }
}
