package com.example.testtask.ui.registrationView.presenter;

import com.example.testtask.data.base.Repository;
import com.example.testtask.data.base.callback.UserRegistrationListener;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.data.base.model.User;
import com.example.testtask.ui.registrationView.view.RegistrationView;

public class RegistrationPresenterImpl implements RegistrationPresenter {

    private static final String EMPTY_LOGIN = "Please enter login";
    private static final String EMPTY_PASSWORD = "Please enter password";

    private Repository repository;
    private RegistrationView view;

    public RegistrationPresenterImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void attachView(RegistrationView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void clickRegistration(final String login, final String password) {
        if (login.isEmpty()) {
            view.showMessage(EMPTY_LOGIN);
            return;
        }

        if (password.isEmpty()) {
            view.showMessage(EMPTY_PASSWORD);
            return;
        }

        repository.registerUser(new User(login, password), new UserRegistrationListener() {
            @Override
            public void isRegistered() {
                view.registrationCompleted(login, password);
            }

            @Override
            public void error(RepositoryException ex) {
                view.showMessage(ex.getMessage());
            }
        });
    }
}
