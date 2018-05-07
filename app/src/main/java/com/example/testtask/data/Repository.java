package com.example.testtask.data;

public interface Repository {
    void downloadAndSaveFilms();
    void updateFilm(long id, boolean bookmark);
}
