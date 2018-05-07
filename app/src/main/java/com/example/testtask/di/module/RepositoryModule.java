package com.example.testtask.di.module;

import com.example.testtask.data.Repository;
import com.example.testtask.data.RepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    Repository provideRepository() {
        return new RepositoryImpl();
    }
}
