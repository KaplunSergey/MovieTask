package com.example.testtask.database;

import com.example.testtask.App;

import javax.inject.Inject;

public class StorageImpl implements Storage {

    @Inject
    DbHelper dbHelper;

    public StorageImpl() {
        App.getStorageComponent().inject(this);
    }
}
