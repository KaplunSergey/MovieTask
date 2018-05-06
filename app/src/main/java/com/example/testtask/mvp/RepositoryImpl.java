package com.example.testtask.mvp;

import com.example.testtask.App;
import com.example.testtask.database.Storage;
import com.example.testtask.network.Network;

import javax.inject.Inject;

public class RepositoryImpl implements Repository {

    @Inject
    Network network;

    @Inject
    Storage storage;

    public RepositoryImpl() {
        App.getRepositoryComponent().inject(this);
    }
}
