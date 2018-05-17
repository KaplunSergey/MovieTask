package com.example.testtask.data.base;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.Utils.NetworkUtils;
import com.example.testtask.data.base.callback.UserRegistrationListener;
import com.example.testtask.data.base.callback.UserValidationListener;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.data.base.model.Movie;
import com.example.testtask.data.base.model.User;
import com.example.testtask.data.database.key.KeyDb;
import com.example.testtask.data.database.user.UserDb;
import com.example.testtask.data.encryption.RSAEncryption;
import com.example.testtask.data.network.callback.MovieDownloadListener;
import com.example.testtask.data.base.callback.MovieListener;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.database.movie.MovieDb;
import com.example.testtask.data.network.Network;
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
    private static final String LOADING_ERROR = "Could not download data";
    private static final String NETWORK_ERROR = "Can not download data, check the connection to the Internet";
    private static final String MODEL_NOT_FOUND = "Model not found";
    private static final String MODELS_NOT_FOUND = "Models not found";

    private Network network;
    private Storage storage;
    private Context context;
    private RSAEncryption encryption;

    public RepositoryImpl(Network network, Storage storage, Context context, RSAEncryption encryption) {
        this.network = network;
        this.storage = storage;
        this.context = context;
        this.encryption = encryption;

        registerKeys();
    }

    @Override
    public void getMovies(final MovieListener listener) {
        if (storage.moviesDownloaded()) {
            downloadMoviesByStorage(listener);
            return;
        }

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

    @Override
    public void validateUser(User user, UserValidationListener listener) {
        UserDb userDb = storage.getUserByLogin(user.getLogin());

        if (userDb == null) {
            listener.error(new RepositoryException("We cannot find an account"));
            return;
        }

        KeyDb key = storage.getKey();

        if (key == null) {
            listener.error(new RepositoryException("Authorization error"));
            return;
        }

        String password = null;
        try {
            password = encryption.decrypt(userDb.getPassword(), key.getPrivateKey());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!user.getPassword().equals(password)) {
            listener.error(new RepositoryException("We cannot find an account"));
            return;
        }

        listener.isValidation();
    }

    @Override
    public void registerUser(User user, UserRegistrationListener listener) {
        UserDb userDb = storage.getUserByLogin(user.getLogin());

        if (userDb != null) {
            listener.error(new RepositoryException("Such login is already in use"));
            return;
        }

        KeyDb key = storage.getKey();

        if (key == null) {
            //TODO error handing
            listener.error(new RepositoryException("Register error"));
            return;
        }

        String password = null;
        try {
            password = encryption.encrypt(user.getPassword(), key.getPublicKey());
        } catch (Exception e) {
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
    }

    private void downloadMoviesByStorage(MovieListener listener) {
        List<MovieDb> movies = storage.getMovies();

        if (movies == null) {
            listener.error(new RepositoryException(MODELS_NOT_FOUND));
            return;
        }

        listener.getMovies(MovieUtils.getMoviesByMovieDbList(movies));
    }


}
