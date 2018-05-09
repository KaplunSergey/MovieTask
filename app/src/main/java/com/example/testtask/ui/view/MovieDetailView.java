package com.example.testtask.ui.view;

import com.example.testtask.data.database.Movie;

public interface MovieDetailView {
    void setMovieId(int movieId);
    void updateBookmarkButton(boolean bookmark);
    void showMovie(Movie movie);
}
