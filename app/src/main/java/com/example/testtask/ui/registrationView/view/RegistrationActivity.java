package com.example.testtask.ui.registrationView.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.testtask.App;
import com.example.testtask.ui.registrationView.presenter.RegistrationPresenter;

import javax.inject.Inject;

public class RegistrationActivity extends AppCompatActivity implements RegistrationView {

    @Inject
    RegistrationPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
    }
}
