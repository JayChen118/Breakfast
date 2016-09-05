package com.example.administrator.breakfast.presentation.base;

/**
 * Created by Jay on 2016/9/5.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
