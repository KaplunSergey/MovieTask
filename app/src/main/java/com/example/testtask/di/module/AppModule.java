package com.example.testtask.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;

    public AppModule(@NonNull Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }
}
