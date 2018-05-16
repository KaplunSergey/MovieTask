package com.example.testtask.ui.authorizationView.presenter;

import com.example.testtask.data.base.Repository;

public class AuthorizationPresenterImpl implements AuthorizationPresenter {

    private Repository repository;

    public AuthorizationPresenterImpl(Repository repository) {
        this.repository = repository;
    }
}
