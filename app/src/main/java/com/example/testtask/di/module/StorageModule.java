package com.example.testtask.di.module;

import android.support.annotation.NonNull;

import com.example.testtask.database.Storage;
import com.example.testtask.database.StorageImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {
    @Provides
    @Singleton
    @NonNull
    Storage provideStorage() {
        return new StorageImpl();
    }
}
