package com.example.testtask.data.base;

import com.example.testtask.data.base.callback.MovieDownloadListener;
import com.example.testtask.data.base.callback.MoviesDownloadListener;
import com.example.testtask.data.base.callback.UserRegistrationListener;
import com.example.testtask.data.base.callback.UserValidationListener;
import com.example.testtask.data.base.model.Movie;
import com.example.testtask.data.base.model.User;

public interface Repository {

    void getMovies(MoviesDownloadListener listener);

    void getMovie(int id, MovieDownloadListener listener);

    void updateMovieBookmark(Movie movie);

    void downloadMovies(MoviesDownloadListener listener);

    void validateUser(User user, UserValidationListener listener);

    void registerUser(User user, UserRegistrationListener listener);

    void close();

}
