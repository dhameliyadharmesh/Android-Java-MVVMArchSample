package com.java.mvvmsample.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.common.BaseViewBindingActivity;
import com.common.UIUtils;
import com.common.viewmodel.CustomViewModelProvider;
import com.java.mvvmsample.app.App;
import com.java.mvvmsample.databinding.LoginActivityBinding;
import com.java.mvvmsample.ui.user.UserRepository;
import com.java.mvvmsample.ui.user.UserServices;

public class LoginActivity extends BaseViewBindingActivity<LoginActivityBinding> {

    private LoginViewModel viewModel;

    @Override
    public LoginActivityBinding inflateLayout(LayoutInflater layoutInflater) {
        return LoginActivityBinding.inflate(layoutInflater);
    }

    @Override
    public void onActivityCreate(Bundle bundle) {
        UserServices userServices = ((App) getApplication()).getRetrofit().create(UserServices.class);
        UserRepository userRepository = new UserRepository(userServices);
        CustomViewModelProvider viewModelProvider = new CustomViewModelProvider(userRepository);
        viewModel = new ViewModelProvider(this, viewModelProvider).get(LoginViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        initObservers();
        initViewClickListeners();
    }

    private void initObservers() {
        viewModel.getFormData().observe(this, loginModel -> {
            UIUtils.hideKeyboard(getWindow());
            viewModel.login(loginModel);
        });

        viewModel.getLoginApiData().observe(this, jsonObject ->
                viewModel.onLoginResponseObserved(jsonObject));

        viewModel.getToastData().observe(this, strResId ->
                UIUtils.showToast(LoginActivity.this, strResId));
    }

    private void initViewClickListeners() {
        binding.btnLogin.setOnClickListener(view -> viewModel.performValidation());
    }
}