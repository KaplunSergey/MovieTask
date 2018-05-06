package com.example.testtask.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.testtask.App;
import com.example.testtask.R;
import com.example.testtask.mvp.MovieView;
import com.example.testtask.mvp.Presenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MovieView {

    @Inject
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getPresenterComponent().inject(this);
        presenter.addView(this);
    }
}
