package com.common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.common.livedata.EventData;

/**
 * @Authoer Dharmesh
 * @Date 24-02-2022
 * <p>
 * Information
 **/
public class BaseViewModel extends ViewModel {

    private MutableLiveData<EventData<Integer>> toastData;
    private MutableLiveData<EventData<String>> toastStrData;

    public MutableLiveData<EventData<Integer>> getToastData() {
        return toastData = (toastData == null) ? new MutableLiveData<>() : toastData;
    }

    public MutableLiveData<EventData<String>> getToastStrData() {
        return toastStrData = (toastStrData == null) ? new MutableLiveData<>() : toastStrData;
    }
}
