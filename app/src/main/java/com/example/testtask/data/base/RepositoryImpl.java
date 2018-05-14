package com.example.testtask.data.base;

import android.content.Context;
import android.net.NetworkInfo;

import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.Utils.NetworkUtils;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.data.network.callback.MovieDownloadListener;
import com.example.testtask.data.base.callback.MovieListener;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.database.movie.MovieDb;
import com.example.testtask.data.network.Network;
import com.example.testtask.data.network.movie.MovieNet;

import java.util.List;

public class RepositoryImpl implements Repository {

    /**
     * It's better to have enum with error codes and simple description
     *
     * Look for enum with parameters
     */
    private static final String LOADING_ERROR = "Could not download data";
    private static final String NETWORK_ERROR = "Can not download data, check the connection to the Internet";
    private static final String MODEL_NOT_FOUND = "Model not found";
    private static final String MODELS_NOT_FOUND = "Models not found";

    private Network network;
    private Storage storage;
    // NetworkUtils networkUtils
    private Context context;

    // + networkUtils
    public RepositoryImpl(Network network, Storage storage, Context context) {
        this.network = network;
        this.storage = storage;
        this.context = context;
    }

    @Override
    public void getMovies(final MovieListener listener) {
        if (storage.moviesDownloaded()) {
            downloadMoviesByStorage(listener);
            return;
        }

        /**
         * networkUtils.isConnected()
         *
         * without context
         */
        if (NetworkUtils.getState(context) == NetworkInfo.State.CONNECTED) {
            downloadMoviesByNetwork(listener);
            return;
        }

        listener.error(new RepositoryException(NETWORK_ERROR));
    }

    @Override
    public Movie getMovie(int id) throws RepositoryException {
        MovieDb movie = storage.getMovie(id);
        if (movie == null) {
            throw new RepositoryException(MODEL_NOT_FOUND);
        }
        return MovieUtils.getMovieByMovieDb(movie);
    }

    @Override
    public void updateMovieBookmark(Movie movie) {
        storage.updateMoviesBookmark(movie.getId(), movie.isBookmark());
    }

    @Override
    public void downloadMovies(final MovieListener listener) {
        if (NetworkUtils.getState(context) == NetworkInfo.State.CONNECTED) {
            downloadMoviesByNetwork(listener);
            return;
        }

        listener.error(new RepositoryException(NETWORK_ERROR));
    }

    private void downloadMoviesByNetwork(final MovieListener listener) {
        network.downloadMovies(new MovieDownloadListener() {
            @Override
            public void moviesDownloaded(List<MovieNet> movies) {

                if (storage.moviesDownloaded()) {
                    storage.deleteMovies();
                }

                storage.addMovies(MovieUtils.getMovieDbByMovieNetList(movies));
                List<MovieDb> dbMovies = storage.getMovies();

                listener.getMovies(MovieUtils.getMoviesByMovieDbList(dbMovies));
            }

            @Override
            public void loadingError(Throwable t) {
                listener.error(new RepositoryException(LOADING_ERROR, t));
            }
        });
    }

    @Override
    public void close() {
        storage.close();
        network.close();
        // or
        network.close(Network.ReqId.GET_MOVIES);
    }

    private void downloadMoviesByStorage(MovieListener listener) {
        List<MovieDb> movies = storage.getMovies();

        /**
         * getMovies could not be NULL !
         */

        if (movies == null) {
            listener.error(new RepositoryException(MODELS_NOT_FOUND));
            return;
        }

        listener.getMovies(MovieUtils.getMoviesByMovieDbList(movies));
    }


}
