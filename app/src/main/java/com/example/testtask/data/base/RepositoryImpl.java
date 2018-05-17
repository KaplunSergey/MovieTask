package com.example.testtask.data.base;

import android.net.NetworkInfo;

import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.Utils.NetworkUtils;
import com.example.testtask.data.base.callback.MovieDownloadListener;
import com.example.testtask.data.base.callback.UserRegistrationListener;
import com.example.testtask.data.base.callback.UserValidationListener;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.data.base.callback.MoviesDownloadListener;
import com.example.testtask.data.base.model.Movie;
import com.example.testtask.data.base.model.User;
import com.example.testtask.data.database.key.KeyDb;
import com.example.testtask.data.database.user.UserDb;
import com.example.testtask.data.encryption.RSAEncryption;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.database.movie.MovieDb;
import com.example.testtask.data.network.Network;
import com.example.testtask.data.network.callback.MovieNetDownloadListener;
import com.example.testtask.data.network.movie.MovieNet;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RepositoryImpl implements Repository {

    private Network network;
    private Storage storage;
    private NetworkUtils networkUtils;
    private RSAEncryption encryption;

    public RepositoryImpl(Network network, Storage storage, NetworkUtils networkUtils, RSAEncryption encryption) {
        this.network = network;
        this.storage = storage;
        this.networkUtils = networkUtils;
        this.encryption = encryption;

        registerKeys();
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
    public void validateUser(User user, UserValidationListener listener) {
        UserDb userDb = storage.getUserByLogin(user.getLogin());

        if (userDb == null) {
            listener.error(new RepositoryException(Errors.CANNOT_FIND_USER.getDescription()));
            return;
        }

        KeyDb key = storage.getKey();
        String password = null;

        try {
            password = encryption.decrypt(userDb.getPassword(), key.getPrivateKey());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }


        if (!user.getPassword().equals(password)) {
            listener.error(new RepositoryException(Errors.CANNOT_FIND_USER.getDescription()));
            return;
        }

        listener.isValidation();
    }

    @Override
    public void registerUser(User user, UserRegistrationListener listener) {
        UserDb userDb = storage.getUserByLogin(user.getLogin());

        if (userDb != null) {
            listener.error(new RepositoryException(Errors.LOGIN_IS_USED.getDescription()));
            return;
        }

        KeyDb key = storage.getKey();
        String password = null;

        try {
            password = encryption.encrypt(user.getPassword(), key.getPublicKey());
        } catch (BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException e) {
            e.printStackTrace();
        }


        UserDb newUser = new UserDb();
        newUser.setLogin(user.getLogin());
        newUser.setPassword(password);

        storage.addUser(newUser);
        listener.isRegistered();
    }

    private void registerKeys() {
        if (storage.getKey() != null) {
            return;
        }

        try {
            KeyPair keyPair = encryption.generateKeys();
            KeyDb key = new KeyDb();
            key.setPublicKey(encryption.getPublicKey(keyPair.getPublic()));
            key.setPrivateKey(encryption.getPrivateKey(keyPair.getPrivate()));
            storage.addKey(key);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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
        MOVIES_NOT_FOUND(3, "Movies not found"),
        CANNOT_FIND_USER(4, "We cannot find an account"),
        LOGIN_IS_USED(5, "Such login is already in use");


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
