package com.example.testtask.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.testtask.data.database.DbHelper;
import com.example.testtask.data.database.movie.MovieDAO;
import com.example.testtask.data.database.movie.MovieDAOImpl;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.database.StorageImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {
    @Provides
    @Singleton
    @NonNull
    Storage provideStorage(MovieDAO movieDAO) {
        return new StorageImpl(movieDAO);
    }

    @Provides
    @Singleton
    @NonNull
    MovieDAO provideMovieDAO(DbHelper dbHelper) {
        return new MovieDAOImpl(dbHelper);
    }

    @Provides
    @Singleton
    @NonNull
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }
}
