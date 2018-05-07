package com.example.testtask.data.database;

import java.util.List;

public interface DbHandler {
    void addFilms(List<Film> films);
    Film getFilm(long id);
    List<Film> getAllFilms();
    void updateBookmark(long id, boolean bookmark);
}
