package com.example.testtask.ui.authorizationView.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.testtask.App;
import com.example.testtask.ui.authorizationView.presenter.AuthorizationPresenter;

import javax.inject.Inject;

public class AuthorizationActivity extends AppCompatActivity implements AuthorizationView {

    @Inject
    AuthorizationPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
    }
}
