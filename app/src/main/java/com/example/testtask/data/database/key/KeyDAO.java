package com.example.testtask.data.database.key;

public interface KeyDAO {

    void addKey(KeyDb keyDb);

    KeyDb getKey();

    void updateKey(KeyDb keyDb);

    void deleteKey(KeyDb keyDb);

    void close();
}
