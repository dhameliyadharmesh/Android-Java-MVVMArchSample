package com.common;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.viewbinding.ViewBinding;

import com.common.managers.NetworkManager;
import com.common.utils.UIUtils;
import com.java.mvvmsample.app.App;

/**
 * @Author Dharmesh
 * @Date 22-02-2022
 * <p>
 * Information
 **/
public abstract class BaseViewBindingActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected VB binding;

    public abstract VB inflateLayout(LayoutInflater layoutInflater);

    public abstract void onActivityCreate(Bundle bundle);

    public abstract void onNetworkChanged(boolean isConnected);

    protected BaseViewModel baseViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateLayout(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        onActivityCreate(savedInstanceState);
        initObservers();
        initNetworkObserver();
    }

    private void initObservers() {
        baseViewModel.getToastData().observe(this, strResId -> {
            if (strResId.getContentIfNotHandled() == null) return;
            UIUtils.showToast(BaseViewBindingActivity.this, strResId.peekContent());
        });


        baseViewModel.getToastStrData().observe(this, str ->{
            if (str.getContentIfNotHandled() == null) return;
            UIUtils.showToast(BaseViewBindingActivity.this, str.peekContent());
        });

    }

    private void initNetworkObserver() {
        App app = (App) getApplication();
        NetworkManager networkManager = app.getNetworkManager();
        MutableLiveData<Boolean> networkData = networkManager.getNetworkData();
        networkData.observe(this, this::onNetworkChanged);
        onNetworkChanged(networkData.getValue());
    }
}
