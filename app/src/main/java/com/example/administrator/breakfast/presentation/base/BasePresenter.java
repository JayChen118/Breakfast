package com.example.administrator.breakfast.presentation.base;

/**
 * Created by Jay on 2016/9/5.
 */

public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T view;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void checkViewAttached(){
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public T getView() {
        return view;
    }

    private boolean isViewAttached() {
        return view != null;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
        }
    }

}
