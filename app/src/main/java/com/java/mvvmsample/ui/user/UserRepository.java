package com.java.mvvmsample.ui.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

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

    public void loginUser(MutableLiveData<JsonObject> loginCallObservable,LoginModel loginModel) {
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
                loginCallObservable.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Timber.d("ZIG onFailure String %s", t.toString());
                loginCallObservable.postValue(null);
            }

        });
    }
}
