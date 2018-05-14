package com.example.testtask.data.database;

import com.example.testtask.data.database.movie.MovieDb;
import com.example.testtask.data.database.movie.MovieDAO;

import java.util.List;

public class StorageImpl implements Storage {

    private MovieDAO movieDAO;

    public StorageImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
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
    public void close() {
        movieDAO.closeDb();
    }
}
