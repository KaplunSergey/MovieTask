package com.example.testtask.ui.authorizationView.presenter;

import com.example.testtask.ui.authorizationView.view.AuthorizationView;
import com.example.testtask.ui.base.BasePresenter;

public interface AuthorizationPresenter extends BasePresenter<AuthorizationView> {

    void clickLogin(String login, String password);

    void clickRegistration();

}
