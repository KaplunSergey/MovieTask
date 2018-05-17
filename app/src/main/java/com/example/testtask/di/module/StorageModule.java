package com.example.testtask.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.testtask.data.database.DbHelper;
import com.example.testtask.data.database.key.KeyDAO;
import com.example.testtask.data.database.key.KeyDAOImpl;
import com.example.testtask.data.database.movie.MovieDAO;
import com.example.testtask.data.database.movie.MovieDAOImpl;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.database.StorageImpl;
import com.example.testtask.data.database.user.UserDAO;
import com.example.testtask.data.database.user.UserDAOImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {
    @Provides
    @NonNull
    Storage provideStorage(MovieDAO movieDAO, KeyDAO keyDAO, UserDAO userDAO) {
        return new StorageImpl(movieDAO, keyDAO, userDAO);
    }

    @Provides
    @NonNull
    MovieDAO provideMovieDAO(DbHelper dbHelper) {
        return new MovieDAOImpl(dbHelper);
    }

    @Provides
    @NonNull
    KeyDAO provideKeyDAO(DbHelper dbHelper) {
        return new KeyDAOImpl(dbHelper);
    }

    @Provides
    @NonNull
    UserDAO provideUserDao(DbHelper dbHelper) {
        return new UserDAOImpl(dbHelper);
    }

    @Provides
    @Singleton
    @NonNull
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }
}
