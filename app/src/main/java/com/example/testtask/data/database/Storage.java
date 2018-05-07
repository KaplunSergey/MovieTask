package com.example.testtask.data.database;

import java.util.List;

public interface Storage {
    void addFilms(List<Film> films);
    Film getFilm(long id);
    List<Film> getAllFilms();
    void updateBookmarkInFilm(long id, boolean bookmark);
}
