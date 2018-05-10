package com.example.testtask.ui.view;

import com.example.testtask.data.database.Movie;

public interface MovieDetailView extends View {
    void updateBookmarkButton(boolean bookmark);
    void showMovie(Movie movie);
}
