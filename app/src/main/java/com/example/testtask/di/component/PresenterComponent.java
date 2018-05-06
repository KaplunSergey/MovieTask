package com.example.testtask.di.component;

import com.example.testtask.mvp.MainActivity;
import com.example.testtask.di.module.PresenterModule;

import dagger.Component;

@Component(modules = {PresenterModule.class})
public interface PresenterComponent {
    void inject(MainActivity mainActivity);
}
