package com.example.testtask.data.base;

import android.net.NetworkInfo;

import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.Utils.NetworkUtils;
import com.example.testtask.data.base.callback.MovieDownloadListener;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.data.base.callback.MoviesDownloadListener;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.database.movie.MovieDb;
import com.example.testtask.data.network.Network;
import com.example.testtask.data.network.callback.MovieNetDownloadListener;
import com.example.testtask.data.network.movie.MovieNet;

import java.util.List;

public class RepositoryImpl implements Repository {

    /**
     * It's better to have enum with error codes and simple description
     *
     * Look for enum with parameters
     */

    private Network network;
    private Storage storage;
    private NetworkUtils networkUtils;

    public RepositoryImpl(Network network, Storage storage, NetworkUtils networkUtils) {
        this.network = network;
        this.storage = storage;
        this.networkUtils = networkUtils;
    }

    @Override
    public void getMovies(final MoviesDownloadListener listener) {
        if (storage.moviesDownloaded()) {
            downloadMoviesByStorage(listener);
            return;
        }

        if (networkUtils.getNetworkState() == NetworkInfo.State.CONNECTED) {
            downloadMoviesByNetwork(listener);
            return;
        }

        listener.error(new RepositoryException(Errors.NETWORK_ERROR.description));
    }

    @Override
    public void getMovie(int id, final MovieDownloadListener listener) {
        MovieDb movieDb = storage.getMovie(id);

        if (movieDb == null) {
            listener.error(new RepositoryException(Errors.MOVIE_NOT_FOUND.description));
            return;
        }

        listener.movieDownloaded(MovieUtils.getMovieByMovieDb(movieDb));
    }

    @Override
    public void updateMovieBookmark(Movie movie) {
        storage.updateMoviesBookmark(movie.getId(), movie.isBookmark());
    }

    @Override
    public void downloadMovies(final MoviesDownloadListener listener) {
        if (networkUtils.getNetworkState() == NetworkInfo.State.CONNECTED) {
            downloadMoviesByNetwork(listener);
            return;
        }

        listener.error(new RepositoryException(Errors.NETWORK_ERROR.description));
    }

    private void downloadMoviesByNetwork(final MoviesDownloadListener listener) {
        network.downloadMovies(new MovieNetDownloadListener() {
            @Override
            public void moviesDownloaded(List<MovieNet> movies) {

                if (storage.moviesDownloaded()) {
                    storage.deleteMovies();
                }

                storage.addMovies(MovieUtils.getMovieDbByMovieNetList(movies));
                List<MovieDb> dbMovies = storage.getMovies();

                listener.moviesDownloaded(MovieUtils.getMoviesByMovieDbList(dbMovies));
            }

            @Override
            public void loadingError(Throwable t) {
                listener.error(new RepositoryException(Errors.LOADING_ERROR.description, t));
            }
        });
    }

    @Override
    public void close() {
        storage.close();
        network.close(Network.ReqId.GET_MOVIES);
    }

    private void downloadMoviesByStorage(MoviesDownloadListener listener) {
        List<MovieDb> movies = storage.getMovies();

        /**
         * moviesDownloaded could not be NULL !
         */

        if (movies == null) {
            listener.error(new RepositoryException(Errors.MOVIES_NOT_FOUND.description));
            return;
        }

        listener.moviesDownloaded(MovieUtils.getMoviesByMovieDbList(movies));
    }

    private enum Errors {
        LOADING_ERROR(0, "Could not download data"),
        NETWORK_ERROR(1, "Can not download data, check the connection to the Internet"),
        MOVIE_NOT_FOUND(2, "Movie not found"),
        MOVIES_NOT_FOUND(3, "Movies not found");

        private final int code;
        private final String description;

        Errors(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public int getCode() {
            return code;
        }
    }
}
