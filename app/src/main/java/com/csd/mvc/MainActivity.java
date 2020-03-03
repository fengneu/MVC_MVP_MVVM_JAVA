package com.csd.mvc;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText et_account;
    private EditText et_pwd;
    private Button bu_login;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_account = (EditText) findViewById(R.id.et_account);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bu_login = (Button) findViewById(R.id.bu_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        bu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                doLogin(account, pwd);
            }
        });
    }

    private void doLogin(String account, String pwd) {
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(pwd)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    });
                    SystemClock.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
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

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ResponseData responseData = ResponseData.paseFromJson(jsonObject);
                            if ("1" == responseData.getRet()) {
                                Toast.makeText(MainActivity.this, responseData.getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, responseData.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }).start();
        } else {
            Toast.makeText(MainActivity.this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
        }
    }
}
