package com.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 * @Authoer Dharmesh
 * @Date 22-02-2022
 * <p>
 * Information
 **/
public abstract class BaseViewBindingActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected ViewBinding binding;

    public abstract ViewBinding getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getLayout();
        setContentView(binding.getRoot());
    }
}
