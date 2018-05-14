package com.example.testtask.di.component;

import android.app.Application;

import com.example.testtask.di.module.AppModule;
import com.example.testtask.di.module.NetworkModule;
import com.example.testtask.di.module.PresenterModule;
import com.example.testtask.di.module.RepositoryModule;
import com.example.testtask.di.module.StorageModule;
import com.example.testtask.ui.detailView.view.MovieDetailActivity;
import com.example.testtask.ui.mainView.view.MoviesFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, PresenterModule.class,
        RepositoryModule.class, StorageModule.class})
public interface AppComponent {
    void inject(MoviesFragment moviesFragment);
    void inject(MovieDetailActivity movieDetailView);

    @Component.Builder
    interface Builder{
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
}
