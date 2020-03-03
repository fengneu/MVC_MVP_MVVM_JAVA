package com.csd.mvp.presenter;

public interface BasePresenter<T> {

    void bindView(T v);

    void unBindView();
}
