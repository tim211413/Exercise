package com.example.retrofittext;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // 1. 宣告 MyAPIService
    MyAPIService myAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. 透過 Retrofit MANAGER
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的 Call, 此處設置 call 為 myAPIService 中的 getAlbums() 連線
        Call<Albums> call = myAPIService.getAlbums();

        // 4. 執行 call
        call.enqueue(new Callback<Albums>() {
            @Override
            public void onResponse(Call<Albums> call, Response<Albums> response) {
                // 連線成功
                // 回傳的資料已轉成 Albums 物件, 可直接用 get 方法取得特定欄位
                String title = response.body().getTitle();
                Log.d("title", title);
            }

            @Override
            public void onFailure(Call<Albums> call, Throwable t) {
                // 連線失敗
            }
        });
    }
}