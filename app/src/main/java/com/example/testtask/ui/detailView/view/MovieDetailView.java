package com.example.testtask.ui.detailView.view;

import com.example.testtask.ui.base.BaseView;
import com.example.testtask.data.base.model.Movie;

public interface MovieDetailView extends BaseView {

    void updateBookmarkButton(boolean bookmark);

    void showMovie(Movie movie);

}
