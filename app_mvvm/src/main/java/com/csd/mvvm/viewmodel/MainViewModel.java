package com.csd.mvvm.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.csd.mvvm.model.ResponseData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class MainViewModel {

    private Context context;
    public ObservableField<String> account = new ObservableField<>();
    public ObservableField<String> pwd = new ObservableField<>();
    public ObservableInt showPro = new ObservableInt(View.GONE);

    public MainViewModel(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "MainViewModel{" +
                "account=" + account.get() +
                ", pwd=" + pwd.get() +
                '}';
    }


    public void doLogin(View view) {
        Log.i("CSD", toString());
        if (!TextUtils.isEmpty(account.get()) && !TextUtils.isEmpty(pwd.get())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showPro.set(View.VISIBLE);
                        }
                    });
                    SystemClock.sleep(3000);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showPro.set(View.GONE);
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
                                Toast.makeText(context, responseData.getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, responseData.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }).start();
        } else {
            Toast.makeText(context, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
        }
    }
}

