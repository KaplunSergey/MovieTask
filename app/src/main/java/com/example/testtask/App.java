package com.example.testtask;

import android.app.Application;

import com.example.testtask.di.component.AppComponent;
import com.example.testtask.di.component.DaggerAppComponent;
import com.example.testtask.di.module.AppModule;

public class App extends Application{

    private static AppComponent appComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
