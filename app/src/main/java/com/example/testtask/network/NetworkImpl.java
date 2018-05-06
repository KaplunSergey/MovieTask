package com.example.testtask.network;

import com.example.testtask.App;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class NetworkImpl implements Network {

    @Inject
    Retrofit retrofit;

    public NetworkImpl() {
        App.getNetworkComponent().inject(this);
    }
}
