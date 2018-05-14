package com.example.testtask;

import android.app.Application;

import com.example.testtask.di.component.AppComponent;
import com.example.testtask.di.component.DaggerAppComponent;

public class App extends Application{

    private static AppComponent appComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
