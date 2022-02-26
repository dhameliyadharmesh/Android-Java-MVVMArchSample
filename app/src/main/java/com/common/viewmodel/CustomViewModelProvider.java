package com.common.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.java.mvvmsample.ui.user.UserRepository;

import java.lang.reflect.InvocationTargetException;

/**
 * @Authoer Dharmesh
 * @Date 26-02-2022
 * <p>
 * Information
 **/
public class CustomViewModelProvider implements ViewModelProvider.Factory {
    private final Object[] newInstance;
    public CustomViewModelProvider(Object... userRepository){
        this.newInstance = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(ViewModel.class).newInstance(newInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
