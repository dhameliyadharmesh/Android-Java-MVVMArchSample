package com.common;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateLayout(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        onActivityCreate(savedInstanceState);
    }
}
