package com.example.testtask.di.component;

import com.example.testtask.data.network.NetworkImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkUtilsModule.class})
public interface NetworkComponent {
    void inject(NetworkImpl network);
}
