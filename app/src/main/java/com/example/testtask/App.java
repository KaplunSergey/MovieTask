package com.example.testtask;

import android.app.Application;

import com.example.testtask.base.Constans;
import com.example.testtask.di.component.DaggerNetworkComponent;
import com.example.testtask.di.component.DaggerPresenterComponent;
import com.example.testtask.di.component.DaggerRepositoryComponent;
import com.example.testtask.di.component.DaggerStorageComponent;
import com.example.testtask.di.component.NetworkComponent;
import com.example.testtask.di.component.PresenterComponent;
import com.example.testtask.di.component.RepositoryComponent;
import com.example.testtask.di.component.StorageComponent;
import com.example.testtask.di.module.AppModule;
import com.example.testtask.di.module.NetworkUtilsModule;

public class App extends Application{

    private static PresenterComponent presenterComponent;
    private static RepositoryComponent repositoryComponent;
    private static NetworkComponent networkComponent;
    private static StorageComponent storageComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        presenterComponent = DaggerPresenterComponent.builder()
                .build();
        repositoryComponent = DaggerRepositoryComponent.builder()
                .build();
        networkComponent = DaggerNetworkComponent.builder()
                .networkUtilsModule(new NetworkUtilsModule(Constans.URL))
                .build();
        storageComponent = DaggerStorageComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static PresenterComponent getPresenterComponent() {
        return presenterComponent;
    }

    public static RepositoryComponent getRepositoryComponent() {
        return repositoryComponent;
    }

    public static NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public static StorageComponent getStorageComponent() {
        return storageComponent;
    }
}
