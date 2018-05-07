package com.example.testtask.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.testtask.data.database.DbHelper;
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
    Storage provideStorage(DbHelper dbHelper) {
        return new StorageImpl(dbHelper);
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }
}
