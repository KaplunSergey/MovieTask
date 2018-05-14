package com.example.testtask.data.base.callback;

import com.example.testtask.data.base.Movie;
import com.example.testtask.data.base.exception.RepositoryException;

import java.util.List;

public interface MovieListener {
    void getMovies(List<Movie> movies);
    void error(RepositoryException ex);
}
