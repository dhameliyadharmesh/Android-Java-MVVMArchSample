package com.common.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author Dharmesh
 * @Date 26-02-2022
 * <p>
 * Information
 **/
public class CustomViewModelProvider implements ViewModelProvider.Factory {

    private final Object[] mConstructorParams;

    public CustomViewModelProvider(Object... constructorParams) {
        mConstructorParams = constructorParams;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == null) {
            throw new IllegalArgumentException("Target ViewModel class can not be null");
        }
        try {
            if (mConstructorParams == null || mConstructorParams.length == 0) {
                return modelClass.newInstance();
            } else {
                Class<?>[] classes = new Class<?>[mConstructorParams.length];
                for (int i = 0; i < mConstructorParams.length; i++) {
                    classes[i] = mConstructorParams[i].getClass();
                }
                return modelClass.getConstructor(classes).newInstance(mConstructorParams);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
