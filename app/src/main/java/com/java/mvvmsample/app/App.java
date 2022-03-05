package com.java.mvvmsample.app;

import android.app.Application;

import com.common.retrofit.RetrofitClient;
import com.java.mvvmsample.BuildConfig;

import retrofit2.Retrofit;
import timber.log.Timber;

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

        initTimberLogging();
        initRetrofitInstance();
    }

    private void initTimberLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initRetrofitInstance() {
        // Add your domain or base url here
        retrofit = RetrofitClient.getRetrofit("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
