package com.example.okhttptext;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    // 建立 OkHttpClient
    //OkHttpClient client = new OkHttpClient().newBuilder().build();

    // OkHttpClient 加入 Interceptor
    OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        // 建立 Request, 設置連線資訊
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts/1")
                .build();

        // 建立 Call
        Call call = client.newCall(request);

        // 執行 Call 連線到網址
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // 連線失敗
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // 連線成功
                String result = response.body().string();
                Log.d("OkHttp result", result);
            }
        });
*/

        // FormBody 放要傳的參數和值
        FormBody formBody = new FormBody.Builder()
                .add("userId", "1")
                .add("id", "1")
                .add("title", "OkHttp post practice")
                .add("body", "Hello World!")
                .build();

        //建立 Request, 設置連線資訊
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .post(formBody) // 使用 post 連線
                .build();

        // 建立 Call
        Call call = client.newCall(request);

        // 執行 Call 連線到網址
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // 連線失敗
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // 連線成功
                String result = response.body().string();
                Log.d("OkHttp result", result);
            }
        });
    }
}