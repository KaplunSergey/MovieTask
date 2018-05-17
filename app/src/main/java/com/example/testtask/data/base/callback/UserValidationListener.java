package com.example.testtask.data.base.callback;

import com.example.testtask.data.base.exception.RepositoryException;

public interface UserValidationListener {

    void isValidation();

    void error(RepositoryException ex);
}
