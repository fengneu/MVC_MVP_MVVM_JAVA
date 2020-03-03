package com.csd.mvp.view;

import com.csd.mvp.model.ResponseData;

public interface MainView extends BaseView {

    void loginSuccess(ResponseData responseData);

    void loginFailed(String error);

}
