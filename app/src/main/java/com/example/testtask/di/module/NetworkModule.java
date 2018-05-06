package com.example.testtask.di.module;

import android.support.annotation.NonNull;

import com.example.testtask.network.Network;
import com.example.testtask.network.NetworkImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    @NonNull
    Network provideNetwork() {
        return new NetworkImpl();
    }
}
