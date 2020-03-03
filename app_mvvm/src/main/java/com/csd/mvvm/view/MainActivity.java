package com.csd.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import com.csd.mvvm.R;
import com.csd.mvvm.databinding.ActivityMainBinding;
import com.csd.mvvm.viewmodel.MainViewModel;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        activityMainBinding.setMainViewModel(mainViewModel);

        activityMainBinding.etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainViewModel.account.set(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        activityMainBinding.etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainViewModel.pwd.set(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
