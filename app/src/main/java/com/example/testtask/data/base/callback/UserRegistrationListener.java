package com.example.testtask.data.base.callback;

import com.example.testtask.data.base.exception.RepositoryException;

public interface UserRegistrationListener {

    void isRegistered();

    void error(RepositoryException ex);
}
