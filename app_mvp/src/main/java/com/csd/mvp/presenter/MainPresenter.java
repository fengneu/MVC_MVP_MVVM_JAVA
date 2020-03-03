package com.csd.mvp.presenter;

import com.csd.mvp.view.MainView;

public interface MainPresenter extends BasePresenter<MainView> {

    void doLogin(String account, String pwd);

}
