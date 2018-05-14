package com.example.testtask.data.base;

import com.example.testtask.data.base.callback.MovieListener;
import com.example.testtask.data.base.exception.RepositoryException;

public interface Repository {
    void getMovies(MovieListener listener);
    Movie getMovie(int id) throws RepositoryException;
    void updateMovieBookmark(Movie movie);
    void downloadMovies(MovieListener listener);
    void close();
}
