package com.example.testtask.data.database;

import com.example.testtask.App;

import java.util.List;

import javax.inject.Inject;

public class StorageImpl implements Storage {

    @Inject
    DbHelper dbHelper;

    public StorageImpl() {
        App.getStorageComponent().inject(this);
    }

    @Override
    public void addFilms(List<Film> films) {

    }

    @Override
    public Film getFilm(long id) {
        return null;
    }

    @Override
    public List<Film> getAllFilms() {
        return null;
    }

    @Override
    public void updateBookmarkInFilm(long id, boolean bookmark) {

    }
}
