package com.example.retrofittext;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager mInstance = new RetrofitManager();
    private MyAPIService myAPIService;
    private RetrofitManager() {
        // 設置 baseUrl 即要連的網站, addConverterFactory 用 Gson 作為資料處力 Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.tyicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPIService = retrofit.create(MyAPIService.class);
    }

    public static RetrofitManager getInstance() {
        return mInstance;
    }

    public MyAPIService getAPI() {
        return myAPIService;
    }
}
