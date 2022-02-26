package com.java.mvvmsample.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.common.BaseViewBindingActivity;
import com.common.viewmodel.CustomViewModelProvider;
import com.java.mvvmsample.app.App;
import com.java.mvvmsample.databinding.LoginActivityBinding;
import com.java.mvvmsample.ui.user.UserRepository;
import com.java.mvvmsample.ui.user.UserServices;

public class LoginActivity extends BaseViewBindingActivity<LoginActivityBinding> {

    private LoginViewModel loginViewModel;

    @Override
    public LoginActivityBinding inflateLayout(LayoutInflater layoutInflater) {
        return LoginActivityBinding.inflate(layoutInflater);
    }

    @Override
    public void onActivityCreate(Bundle bundle) {


        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        UserServices userServices = ((App)getApplication()).getRetrofit().create(UserServices.class);
        loginViewModel.setUserRepository(new UserRepository(userServices));
        binding.setLifecycleOwner(this);
        binding.setViewModel(loginViewModel);

        initObservers();
        initViewClickListeners();
    }

    private void initObservers() {
        loginViewModel.getUser().observe(this, loginModel -> {
            if (loginModel != null) {
                Log.d("DDD", "Valid data entered");
                login(loginModel);
            }
        });

        loginViewModel.getLoginCallObservable().observe(this,jsonObject -> {
            if(jsonObject == null){
                Log.d("DDD","Error");
            }else{
                Log.d("DDD",jsonObject.toString());
            }
        });
    }

    private void login(LoginModel loginModel) {
        loginViewModel.login(loginModel);
    }

    private void initViewClickListeners() {
        binding.btnLogin.setOnClickListener(view -> {
            loginViewModel.validateForm();
        });
    }


}