package com.csd.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.csd.mvp.R;
import com.csd.mvp.model.ResponseData;
import com.csd.mvp.presenter.MainPresenterImp;


public class MainActivity extends AppCompatActivity implements MainView {

    private EditText et_account;
    private EditText et_pwd;
    private Button bu_login;
    private ProgressBar progressBar;

    private MainPresenterImp mainPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_account = (EditText) findViewById(R.id.et_account);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bu_login = (Button) findViewById(R.id.bu_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mainPresenterImp = new MainPresenterImp(this);
        mainPresenterImp.bindView(this);
        bu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                mainPresenterImp.doLogin(account, pwd);
            }
        });
    }

    @Override
    public void loginSuccess(ResponseData responseData) {
        Toast.makeText(MainActivity.this, responseData.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginFailed(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dissProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


}
