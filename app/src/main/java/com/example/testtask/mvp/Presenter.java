package com.example.testtask.mvp;

import android.support.annotation.NonNull;

public interface Presenter {
    void addView(@NonNull MovieView movieView);
}
