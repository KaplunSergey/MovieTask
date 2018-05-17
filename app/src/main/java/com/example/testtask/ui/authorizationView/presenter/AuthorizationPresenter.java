package com.example.testtask.ui.authorizationView.presenter;

import com.example.testtask.base.BasePresenter;
import com.example.testtask.ui.authorizationView.view.AuthorizationView;

public interface AuthorizationPresenter extends BasePresenter<AuthorizationView> {
    void clickLogin(String login, String password);
    void clickRegistration();
}
