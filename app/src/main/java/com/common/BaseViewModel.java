package com.common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @Authoer Dharmesh
 * @Date 24-02-2022
 * <p>
 * Information
 **/
public class BaseViewModel extends ViewModel {

    private MutableLiveData<Integer> toastData;
    private MutableLiveData<String> toastStrData;

    public MutableLiveData<Integer> getToastData() {
        return toastData = (toastData == null) ? new MutableLiveData<>() : toastData;
    }

    public MutableLiveData<String> getToastStrData() {
        return toastStrData = (toastStrData == null) ? new MutableLiveData<>() : toastStrData;
    }
}
