package com.example.testtask.di.module;

import com.example.testtask.mvp.Presenter;
import com.example.testtask.mvp.PresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    Presenter providePresenter() {
        return new PresenterImpl();
    }
}
