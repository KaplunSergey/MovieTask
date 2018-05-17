package com.example.testtask.data.database;

import com.example.testtask.data.database.key.KeyDAO;
import com.example.testtask.data.database.key.KeyDb;
import com.example.testtask.data.database.movie.MovieDb;
import com.example.testtask.data.database.movie.MovieDAO;
import com.example.testtask.data.database.user.UserDAO;
import com.example.testtask.data.database.user.UserDb;
import com.example.testtask.data.database.user.UserTable;

import java.util.List;

public class StorageImpl implements Storage {

    private MovieDAO movieDAO;
    private KeyDAO keyDAO;
    private UserDAO userDAO;

    public StorageImpl(MovieDAO movieDAO, KeyDAO keyDAO, UserDAO userDAO) {
        this.movieDAO = movieDAO;
        this.keyDAO = keyDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void addMovies(List<MovieDb> movies) {
        movieDAO.addMovies(movies);
    }

    @Override
    public MovieDb getMovie(int id) {
        return movieDAO.getMovie(id);
    }

    @Override
    public List<MovieDb> getMovies() {
        return movieDAO.getMovies();
    }

    @Override
    public void updateMoviesBookmark(int id, boolean bookmark) {
        movieDAO.updateMovieBookmark(id, bookmark);
    }

    @Override
    public boolean moviesDownloaded() {
        return movieDAO.moviesDownloaded();
    }

    @Override
    public void deleteMovies() {
        movieDAO.deleteMovies();
    }

    @Override
    public KeyDb getKey() {
        return keyDAO.getKey();
    }

    @Override
    public void addKey(KeyDb key) {
        keyDAO.addKey(key);
    }

    @Override
    public UserDb getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public void addUser(UserDb user) {
        userDAO.addUser(user);
    }

    @Override
    public void close() {
        movieDAO.closeDb();
        keyDAO.close();
        userDAO.close();
    }
}
