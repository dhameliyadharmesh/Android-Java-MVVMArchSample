package com.java.mvvmsample.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.common.BaseViewBindingActivity;
import com.common.utils.UIUtils;
import com.common.viewmodel.CustomViewModelProvider;
import com.java.mvvmsample.app.App;
import com.java.mvvmsample.databinding.LoginActivityBinding;
import com.java.mvvmsample.ui.user.UserRepository;
import com.java.mvvmsample.ui.user.UserServices;

import timber.log.Timber;

public class LoginActivity extends BaseViewBindingActivity<LoginActivityBinding> {

    private LoginViewModel viewModel;

    @Override
    public LoginActivityBinding inflateLayout(LayoutInflater layoutInflater) {
        return LoginActivityBinding.inflate(layoutInflater);
    }

    @Override
    public void onActivityCreate(Bundle bundle) {
        Timber.d("Observer onActivityCreate");
        UserServices userServices = ((App) getApplication()).getRetrofit().create(UserServices.class);
        UserRepository userRepository = new UserRepository(userServices);
        CustomViewModelProvider viewModelProvider = new CustomViewModelProvider(userRepository);
        viewModel = new ViewModelProvider(this, viewModelProvider).get(LoginViewModel.class);
        baseViewModel = viewModel;
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        initObservers();
        initViewClickListeners();
    }

    @Override
    public void onNetworkChanged(boolean isConnected) {
//        UIUtils.showToast(LoginActivity.this, (isConnected) ? R.string.connected : R.string.disconnected);
    }

    private void initObservers() {
        viewModel.getFormData().observe(this, loginModel -> {
            if (loginModel.getContentIfNotHandled() == null) return;
            UIUtils.hideKeyboard(getWindow());
            Timber.d("Observer fired");
            viewModel.login(loginModel);
        });

//        viewModel.getFormData().observe(this, new EventObserver(new EventHandler<LoginModel>() {
//
//            @Override
//            public void onEventUnHandled(LoginModel loginModel) {
//                if(loginModel == null) return;
//                UIUtils.hideKeyboard(getWindow());
//                Timber.d("Observer fired");
//                viewModel.login(loginModel);
//            }
//        }));
//
//        viewModel.getLoginApiData().observe(this, new EventObserver(new EventHandler<Resource>() {
//
//            @Override
//            public void onEventUnHandled(Resource jsonObject) {
//                if(jsonObject == null) return;
//                viewModel.onLoginResponseObserved(jsonObject);
//            }
//        }));

        viewModel.getLoginApiData().observe(this, jsonObject -> {
            if (jsonObject.getContentIfNotHandled() == null) return;
            viewModel.onLoginResponseObserved(jsonObject.peekContent());
        });

    }

    private void initViewClickListeners() {
        binding.btnLogin.setOnClickListener(view -> viewModel.performValidation());
    }
}