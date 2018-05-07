package com.example.testtask.di.component;

import com.example.testtask.di.module.PresenterModule;
import com.example.testtask.ui.view.MovieDetailFragment;
import com.example.testtask.ui.view.MoviesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {
    void inject(MovieDetailFragment movieDetailFragment);
    void inject(MoviesFragment moviesFragment);
}
