package com.example.testtask.di.module;

import android.content.Context;

import com.example.testtask.database.DbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageUtilsModule {
    @Provides
    @Singleton
    DbHelper provideDbHelper(Context context) {
       return new DbHelper(context);
    }
}
