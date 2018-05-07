package com.example.testtask.di.module;

import com.example.testtask.data.Repository;
import com.example.testtask.data.RepositoryImpl;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.network.Network;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    Repository provideRepository(Network network, Storage storage) {
        return new RepositoryImpl(network, storage);
    }
}
