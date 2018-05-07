package com.example.testtask.di.module;

import android.support.annotation.NonNull;

import com.example.testtask.data.network.Network;
import com.example.testtask.data.network.NetworkImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://api.androidhive.info/json/movies.json";

    @Provides
    @Singleton
    @NonNull
    Network provideNetwork() {
        return new NetworkImpl();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
    }
}
