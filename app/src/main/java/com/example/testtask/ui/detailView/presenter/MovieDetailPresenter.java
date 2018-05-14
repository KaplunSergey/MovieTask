package com.example.testtask.ui.detailView.presenter;

import com.example.testtask.ui.detailView.view.MovieDetailView;
import com.example.testtask.base.BasePresenter;

public interface MovieDetailPresenter extends BasePresenter<MovieDetailView> {
    void detailViewIsReady(int movieId);
    void bookmarkButtonClicked();
}
