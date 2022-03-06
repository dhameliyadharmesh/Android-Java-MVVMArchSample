package com.common.retrofit;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Authoer Dharmesh
 * @Date 24-02-2022
 * <p>
 * Information
 **/
public class RetrofitClient {

    public static Retrofit getRetrofit(final String API_BASE_URL, Context context) {

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new NetworkInterceptor(context))
//                .build();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
//                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new NetworkInterceptor(context));
        return builder.client(httpClient.build()).build();
    }
}
