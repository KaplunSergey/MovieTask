package com.example.testtask.di.component;

import com.example.testtask.di.module.RepositoryModule;
import com.example.testtask.ui.presenter.MovieDetailPresenterImpl;
import com.example.testtask.ui.presenter.MoviesPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class})
public interface RepositoryComponent {
    void inject(MovieDetailPresenterImpl movieDetailPresenter);
    void inject(MoviesPresenterImpl moviesPresenter);
}
