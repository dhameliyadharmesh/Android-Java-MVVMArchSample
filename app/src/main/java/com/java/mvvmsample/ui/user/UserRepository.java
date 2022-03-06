package com.java.mvvmsample.ui.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.common.exceptions.NoInternetException;
import com.common.retrofit.Resource;
import com.google.gson.JsonObject;
import com.java.mvvmsample.ui.login.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

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

    public void loginUser(MutableLiveData<Resource<JsonObject>> loginCallObservable, LoginModel loginModel) {
        Call<JsonObject> call = userServices.loginUser(loginModel.getStrEmail(), loginModel.getStrPassword());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                Timber.d("ZIG Response Code %s", response.code());
                Timber.d("ZIG Response Success %s", response.isSuccessful());
                Timber.d("ZIG Response Message %s", response.message());
                Timber.d("ZIG Response Error Body %s", response.errorBody());
                Timber.d("ZIG Response Headers %s", response.headers());
                Timber.d("ZIG Response toString %s", response.toString());
                Timber.d("ZIG Response RAW %s", response.raw());
                Timber.d("ZIG Response Body %s", response.body());
                loginCallObservable.postValue(Resource.success(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Timber.d("ZIG onFailure String %s", t.toString());
                loginCallObservable.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    public <T> void login(MutableLiveData<Resource<T>> loginCallObservable, LoginModel loginModel) {
        Call call = userServices.login(loginModel.getStrEmail(), loginModel.getStrPassword());
        apiCall(loginCallObservable,call);
//        call.enqueue(new Callback<T>() {
//            @Override
//            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
//                Timber.d("ZIG Response Code %s", response.code());
//                Timber.d("ZIG Response Success %s", response.isSuccessful());
//                Timber.d("ZIG Response Message %s", response.message());
//                Timber.d("ZIG Response Error Body %s", response.errorBody());
//                Timber.d("ZIG Response Headers %s", response.headers());
//                Timber.d("ZIG Response toString %s", response.toString());
//                Timber.d("ZIG Response RAW %s", response.raw());
//                Timber.d("ZIG Response Body %s", response.body());
//                loginCallObservable.postValue(Resource.success(response.body()));
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
//                Timber.d("ZIG onFailure String %s", t.toString());
//                loginCallObservable.postValue(Resource.error(t.getMessage(), null));
//            }
//        });

    }

    public <T> void apiCall(MutableLiveData<Resource<T>> observable, @NonNull Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                Timber.d("ZIG Response Code %s", response.code());
                Timber.d("ZIG Response Success %s", response.isSuccessful());
                Timber.d("ZIG Response Message %s", response.message());
                Timber.d("ZIG Response Error Body %s", response.errorBody());
                Timber.d("ZIG Response Headers %s", response.headers());
                Timber.d("ZIG Response toString %s", response.toString());
                Timber.d("ZIG Response RAW %s", response.raw());
                Timber.d("ZIG Response Body %s", response.body());
                if(response.isSuccessful() && response.body() != null){
                    observable.postValue(Resource.success(response.body()));
                }else{
                    observable.postValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                Timber.d("ZIG onFailure String ");
                if(t instanceof NoInternetException){
                    observable.postValue(Resource.noInternetError(t.getMessage()));
                } else {
                    observable.postValue(Resource.error(t.getMessage(), null));
                }
            }
        });
    }
}
