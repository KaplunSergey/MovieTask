package com.example.testtask.ui.authorizationView.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testtask.App;
import com.example.testtask.R;
import com.example.testtask.common.Constant;
import com.example.testtask.ui.authorizationView.presenter.AuthorizationPresenter;
import com.example.testtask.ui.mainView.view.MoviesActivity;
import com.example.testtask.ui.registrationView.view.RegistrationActivity;

import javax.inject.Inject;

public class AuthorizationActivity extends AppCompatActivity implements AuthorizationView {

    private static final int REQUEST_CODE = 2;

    @Inject
    AuthorizationPresenter presenter;
    EditText loginField;
    EditText passwordField;
    Button loginButton;
    Button registrationButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization_activity);
        App.getAppComponent().inject(this);

        loginField = findViewById(R.id.login_authorization_field);
        passwordField = findViewById(R.id.password_authorization_field);
        loginButton = findViewById(R.id.login);
        registrationButton = findViewById(R.id.registration);

        presenter.attachView(this);

        loginButton.setOnClickListener(loginListener);
        registrationButton.setOnClickListener(registerListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK) {
            return;
        }

        String login = data.getStringExtra(Constant.USER_LOGIN_FIELD);
        String password = data.getStringExtra(Constant.USER_PASSWORD_FIELD);

        loginField.setText(login);
        passwordField.setText(password);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openMainView() {
        Intent intent = new Intent(this, MoviesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void openRegistrationView() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String login = loginField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            presenter.clickLogin(login, password);
        }
    };

    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.clickRegistration();
        }
    };
}
