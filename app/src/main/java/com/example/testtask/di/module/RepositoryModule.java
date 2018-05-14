package com.example.testtask.di.module;

import android.content.Context;

import com.example.testtask.data.base.Repository;
import com.example.testtask.data.base.RepositoryImpl;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.network.Network;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    Repository provideRepository(Network network, Storage storage, Context context) {
        return new RepositoryImpl(network, storage, context);
    }
}
