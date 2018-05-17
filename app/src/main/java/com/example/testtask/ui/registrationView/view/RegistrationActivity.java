package com.example.testtask.ui.registrationView.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testtask.App;
import com.example.testtask.R;
import com.example.testtask.common.Constant;
import com.example.testtask.ui.authorizationView.view.AuthorizationActivity;
import com.example.testtask.ui.registrationView.presenter.RegistrationPresenter;

import javax.inject.Inject;

public class RegistrationActivity extends AppCompatActivity implements RegistrationView {

    @Inject
    RegistrationPresenter presenter;
    EditText loginField;
    EditText passwordField;
    Button registrationButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        App.getAppComponent().inject(this);

        loginField = findViewById(R.id.login_registration_field);
        passwordField = findViewById(R.id.password_registration_field);
        registrationButton = findViewById(R.id.register);

        presenter.attachView(this);

        registrationButton.setOnClickListener(listener);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String login = loginField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            presenter.clickRegistration(login, password);
        }
    };

    @Override
    public void registrationCompleted(String login, String password) {
        Intent intent = new Intent(this, AuthorizationActivity.class);
        intent.putExtra(Constant.USER_LOGIN_FIELD, login);
        intent.putExtra(Constant.USER_PASSWORD_FIELD, password);
        setResult(RESULT_OK, intent);
        finish();
    }
}
