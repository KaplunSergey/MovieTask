package com.example.testtask.ui.registrationView.presenter;

import com.example.testtask.ui.base.BasePresenter;
import com.example.testtask.ui.registrationView.view.RegistrationView;

public interface RegistrationPresenter extends BasePresenter<RegistrationView> {

    void clickRegistration(String login, String password);

}
