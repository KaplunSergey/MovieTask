package com.example.testtask.data;

import com.example.testtask.callback.MoviesDownloadListener;
import com.example.testtask.data.database.Movie;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.network.Network;

import java.util.List;

public class RepositoryImpl implements Repository {

    private Network network;
    private Storage storage;

    public RepositoryImpl(Network network, Storage storage) {
        this.network = network;
        this.storage = storage;
    }

    @Override
    public void downloadMovies(MoviesDownloadListener moviesDownloadListener) {
        network.downloadMovies(moviesDownloadListener);
    }

    @Override
    public List<Movie> getMovies() {
        return storage.getMovies();
    }

    @Override
    public Movie getMovie(int id) {
        return storage.getMovie(id);
    }

    @Override
    public void saveMovies(List<Movie> movies) {
        storage.addMovies(movies);
    }

    @Override
    public void updateMovieBookmark(int id, boolean bookmark) {
        storage.updateMoviesBookmark(id, bookmark);
    }

    @Override
    public boolean moviesDownloaded() {
        return storage.moviesDownloaded();
    }

    @Override
    public void deleteMovies() {
        storage.deleteMovies();
    }
}
