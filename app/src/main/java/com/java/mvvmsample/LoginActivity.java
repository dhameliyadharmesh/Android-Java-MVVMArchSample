package com.java.mvvmsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.common.BaseActivity;
import com.common.BaseViewBindingActivity;
import com.java.mvvmsample.databinding.LoginActivityBinding;

public class LoginActivity extends BaseViewBindingActivity<LoginActivityBinding> {


    @Override
    public ViewBinding getLayout() {
        return LoginActivityBinding.inflate(LayoutInflater.from(this));
    }


}