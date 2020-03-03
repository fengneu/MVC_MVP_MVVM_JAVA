package com.csd.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;

import com.csd.mvp.model.ResponseData;
import com.csd.mvp.view.MainView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainPresenterImp implements MainPresenter {

    private Context context;
    private MainView mainView;

    public MainPresenterImp(Context context) {
        this.context = context;
    }


    @Override
    public void bindView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void unBindView() {
        mainView = null;
    }

    @Override
    public void doLogin(String account, String pwd) {
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(pwd)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainView.showProgressBar();
                        }
                    });
                    SystemClock.sleep(3000);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainView.dissProgressBar();
                        }
                    });

                    final JSONObject jsonObject = new JSONObject();
                    Random random = new Random();
                    if (random.nextBoolean()) {
                        try {
                            jsonObject.put("ret", "1");
                            jsonObject.put("msg", "登录成功");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            jsonObject.put("ret", "-1");
                            jsonObject.put("msg", "登录失败");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ResponseData responseData = ResponseData.paseFromJson(jsonObject);
                            if ("1" == responseData.getRet()) {
                                mainView.loginSuccess(responseData);
                            } else {
                                mainView.loginFailed(responseData.getMsg());
                            }
                        }
                    });
                }
            }).start();
        } else {
            mainView.showToast("用户名和密码不能为空");
        }
    }
}
