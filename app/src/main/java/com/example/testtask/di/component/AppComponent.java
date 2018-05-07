package com.example.testtask.di.component;

import com.example.testtask.di.module.AppModule;
import com.example.testtask.di.module.NetworkModule;
import com.example.testtask.di.module.PresenterModule;
import com.example.testtask.di.module.RepositoryModule;
import com.example.testtask.di.module.StorageModule;
import com.example.testtask.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, PresenterModule.class,
        RepositoryModule.class, StorageModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
