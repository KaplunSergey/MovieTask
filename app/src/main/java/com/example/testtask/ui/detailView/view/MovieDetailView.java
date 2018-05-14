package com.example.testtask.ui.detailView.view;

import com.example.testtask.base.BaseView;
import com.example.testtask.data.base.Movie;

public interface MovieDetailView extends BaseView {
    void updateBookmarkButton(boolean bookmark);
    void showMovie(Movie movie);
}
