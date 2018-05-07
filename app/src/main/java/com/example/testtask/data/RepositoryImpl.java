package com.example.testtask.data;

import com.example.testtask.App;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.network.Network;

import javax.inject.Inject;

public class RepositoryImpl implements Repository {

    @Inject
    Network network;

    @Inject
    Storage storage;

    public RepositoryImpl() {
        App.getDataComponent().inject(this);
    }

    @Override
    public void downloadAndSaveFilms() {

    }

    @Override
    public void updateFilm(long id, boolean bookmark) {

    }
}
