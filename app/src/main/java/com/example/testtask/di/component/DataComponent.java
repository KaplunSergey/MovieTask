package com.example.testtask.di.component;

import com.example.testtask.di.module.NetworkModule;
import com.example.testtask.di.module.StorageModule;
import com.example.testtask.data.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {StorageModule.class, NetworkModule.class})
public interface DataComponent {
    void inject(RepositoryImpl repository);
}
