package com.example.testtask.data.base;

import com.example.testtask.data.base.callback.MovieListener;
import com.example.testtask.data.base.callback.UserRegistrationListener;
import com.example.testtask.data.base.callback.UserValidationListener;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.data.base.model.Movie;
import com.example.testtask.data.base.model.User;

public interface Repository {

    void getMovies(MovieListener listener);

    Movie getMovie(int id) throws RepositoryException;

    void updateMovieBookmark(Movie movie);

    void downloadMovies(MovieListener listener);

    void validateUser(User user, UserValidationListener listener);

    void registerUser(User user, UserRegistrationListener listener);

    void close();
}
