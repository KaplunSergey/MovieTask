package com.example.testtask.ui.registrationView.presenter;

import com.example.testtask.data.base.Repository;

public class RegistrationPresenterImpl implements RegistrationPresenter {

    private Repository repository;

    public RegistrationPresenterImpl(Repository repository) {
        this.repository = repository;
    }
}
