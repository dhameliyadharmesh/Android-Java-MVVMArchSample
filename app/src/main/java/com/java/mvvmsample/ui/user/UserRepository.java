package com.java.mvvmsample.ui.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.common.exceptions.RetrofitExceptionHandler;
import com.common.livedata.EventData;
import com.common.retrofit.Resource;
import com.java.mvvmsample.ui.login.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author Dharmesh
 * @Date 26-02-2022
 * <p>
 * Information
 **/
public class UserRepository {

    private final UserServices userServices;

    public UserRepository(UserServices userServices) {
        this.userServices = userServices;
    }

    public <T> void login(MutableLiveData<EventData<Resource<T>>> loginCallObservable, LoginModel loginModel) {
        Call call = userServices.login(loginModel.getStrEmail(), loginModel.getStrPassword());
        apiCall(loginCallObservable,call);
    }

    public <T> void apiCall(MutableLiveData<EventData<Resource<T>>> observable, @NonNull Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
//                Timber.d("ZIG Response Code %s", response.code());
//                Timber.d("ZIG Response Success %s", response.isSuccessful());
//                Timber.d("ZIG Response Message %s", response.message());
//                Timber.d("ZIG Response Error Body %s", response.errorBody());
//                Timber.d("ZIG Response Headers %s", response.headers());
//                Timber.d("ZIG Response toString %s", response.toString());
//                Timber.d("ZIG Response RAW %s", response.raw());
//                Timber.d("ZIG Response Body %s", response.body());
                if(response.isSuccessful() && response.body() != null){
                    observable.postValue(new EventData(Resource.success(response.body())));
                }else{
                    new RetrofitExceptionHandler<>(null, observable, response.message()).throwNow();
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                new RetrofitExceptionHandler<>(t,observable,null).throwNow();
            }
        });
    }
}
