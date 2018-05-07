package com.example.testtask.data.database;

import java.util.List;

public class StorageImpl implements Storage {

    DbHelper dbHelper;

    public StorageImpl(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void addMovies(List<Movie> movies) {
        dbHelper.addMovies(movies);
    }

    @Override
    public Movie getMovie(int id) {
        return dbHelper.getMovie(id);
    }

    @Override
    public List<Movie> getMovies() {
        return dbHelper.getMovies();
    }

    @Override
    public void updateMoviesBookmark(int id, boolean bookmark) {
        dbHelper.updateMovieBookmark(id, bookmark);
    }

    @Override
    public boolean moviesDownloaded() {
        return dbHelper.moviesDownloaded();
    }

    @Override
    public void deleteMovies() {
        dbHelper.deleteMovies();
    }
}
