package com.example.testtask.ui.view;

import android.app.Fragment;

import com.example.testtask.App;
import com.example.testtask.ui.presenter.MovieDetailPresenter;

import javax.inject.Inject;

public class MovieDetailFragment extends Fragment implements MovieDetailView{

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    public MovieDetailFragment() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void changeBookmark() {

    }
}
