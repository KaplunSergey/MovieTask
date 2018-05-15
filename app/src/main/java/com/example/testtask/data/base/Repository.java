package com.example.testtask.data.base;

import com.example.testtask.data.base.callback.MovieDownloadListener;
import com.example.testtask.data.base.callback.MoviesDownloadListener;
import com.example.testtask.data.base.exception.RepositoryException;

public interface Repository {

    void getMovies(MoviesDownloadListener listener);

    void getMovie(int id, MovieDownloadListener listener);

    void updateMovieBookmark(Movie movie);

    void downloadMovies(MoviesDownloadListener listener);

    void close();

}
