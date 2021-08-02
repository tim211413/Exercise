package com.example.rxjava_okhttp_retrofit_text;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceManager {
    private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit retrofit;
    String BASE_URL = "https://data.tycg.gov.tw";

    private RetrofitServiceManager() {
        // 創建 OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);

        // 添加公共參數攔截器


        // 創建Retrofit
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static RetrofitServiceManager getInstance() {
        return INSTANCE;
    }

//    public Retrofit getRetrofit() {
//        return mRetrofit;
//    }


    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }

}
