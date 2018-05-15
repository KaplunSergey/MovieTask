package com.example.testtask.ui.base;

/**
 * put this package into 'ui' package
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
