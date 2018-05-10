package com.example.testtask.ui.presenter;

import com.example.testtask.ui.view.View;

public interface Presenter<V extends View> {
    void attachView(V view);
    void detachView();
}
