package com.example.fileload;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final RetrofitManager INSTANCE = new RetrofitManager();
    private static final int DEFAULT_TIME_OUT = 50;
    private static final int DEFAULT_READ_TIME_OUT = 100;
    private Retrofit retrofit;
    String BASE_URL = "http://192.120.97.55";

    private RetrofitManager() {
        // 創建 OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        // 添加公共參數攔截器


        // 創建Retrofit
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static RetrofitManager getInstance() {
        return INSTANCE;
    }

//    public Retrofit getRetrofit() {
//        return mRetrofit;
//    }


    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }

}
