package com.common.exceptions;

import androidx.lifecycle.MutableLiveData;

import com.common.livedata.EventData;
import com.common.retrofit.Resource;

/**
 * @Authoer Dharmesh
 * @Date 06-03-2022
 * <p>
 * Information
 **/
public class RetrofitExceptionHandler<T> {

    private final Throwable t;
    private final String message;
    private final MutableLiveData<EventData<Resource<T>>> observable;

    public RetrofitExceptionHandler(Throwable t,MutableLiveData<EventData<Resource<T>>> observable, String message) {
        this.t = t;
        this.observable = observable;
        this.message = message;
    }

    public void throwNow() {
        if (t instanceof NoInternetException) {
            observable.postValue(new EventData<>(Resource.noInternetError(t.getMessage())));
        } else {
            if (t == null) {
                observable.postValue(new EventData<>(Resource.error(message, null)));
                return;
            }
            observable.postValue(new EventData<>(Resource.error(t.getMessage(), null)));
        }
    }
}
