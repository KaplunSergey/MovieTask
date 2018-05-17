package com.example.testtask.data.database;

import com.example.testtask.data.database.key.KeyDb;
import com.example.testtask.data.database.movie.MovieDb;
import com.example.testtask.data.database.user.UserDb;

import java.util.List;

public interface Storage {

    void addMovies(List<MovieDb> movies);

    MovieDb getMovie(int id);

    List<MovieDb> getMovies();

    void updateMoviesBookmark(int id, boolean bookmark);

    boolean moviesDownloaded();

    void deleteMovies();

    KeyDb getKey();

    void addKey(KeyDb key);

    UserDb getUserByLogin(String login);

    void addUser(UserDb user);

    void close();
}
