package com.java.mvvmsample.app;

import android.app.Application;
import android.content.Context;

import com.common.retrofit.RetrofitClient;

import retrofit2.Retrofit;

/**
 * @Authoer Dharmesh
 * @Date 24-02-2022
 * <p>
 * Information
 **/
public class App extends Application {

    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = RetrofitClient.getRetrofit("https://entertainment97.000webhostapp.com/projects/java_mvvm/");
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
