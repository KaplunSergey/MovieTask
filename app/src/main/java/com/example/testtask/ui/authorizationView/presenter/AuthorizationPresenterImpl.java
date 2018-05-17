package com.example.testtask.ui.authorizationView.presenter;

import com.example.testtask.data.base.Repository;
import com.example.testtask.data.base.callback.UserValidationListener;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.data.base.model.User;
import com.example.testtask.ui.authorizationView.view.AuthorizationView;

public class AuthorizationPresenterImpl implements AuthorizationPresenter {

    private static final String EMPTY_LOGIN = "Please enter login";
    private static final String EMPTY_PASSWORD = "Please enter password";

    private Repository repository;
    private AuthorizationView view;

    public AuthorizationPresenterImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void attachView(AuthorizationView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void clickLogin(String login, String password) {
        if (login.isEmpty()) {
            view.showMessage(EMPTY_LOGIN);
            return;
        }

        if (password.isEmpty()) {
            view.showMessage(EMPTY_PASSWORD);
            return;
        }

        repository.validateUser(new User(login, password), listener);
    }

    @Override
    public void clickRegistration() {
        view.openRegistrationView();
    }

    private UserValidationListener listener = new UserValidationListener() {
        @Override
        public void isValidation() {
            view.openMainView();
        }

        @Override
        public void error(RepositoryException ex) {
            view.showMessage(ex.getMessage());
        }
    };
}
