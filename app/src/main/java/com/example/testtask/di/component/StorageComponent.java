package com.example.testtask.di.component;

import com.example.testtask.database.StorageImpl;
import com.example.testtask.di.module.AppModule;
import com.example.testtask.di.module.StorageUtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {StorageUtilsModule.class, AppModule.class})
public interface StorageComponent {
    void inject(StorageImpl storage);
}
