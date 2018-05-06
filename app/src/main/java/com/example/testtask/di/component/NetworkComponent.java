package com.example.testtask.di.component;

import com.example.testtask.di.module.NetworkUtilsModule;
import com.example.testtask.network.NetworkImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkUtilsModule.class})
public interface NetworkComponent {
    void inject(NetworkImpl network);
}
