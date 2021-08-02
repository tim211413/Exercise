package com.example.rxjava_okhttp_retrofit_text;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {
    private BikeService bikeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bikeService = RetrofitServiceManager.getInstance().create(BikeService.class);

        bikeService.getBike(500, 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        Log.d("Test", "onSubscribe");
                    }

                    @Override
                    public void onNext(@NotNull ResponseBody bike) {
                        Log.d("Test", "onNext");
                        try {
                            Log.d("Test", "Response : " + bike.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.d("Test", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Test", "onComplete");
                    }
                });

        //bikeService.getBike(100, 0)
//        observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Bike>() {
//                    @Override
//                    public void onSubscribe(@org.jetbrains.annotations.NotNull Disposable d) {
//                        // 初始化工作
//
//                    }
//
//                    @Override
//                    public void onNext(@org.jetbrains.annotations.NotNull Bike bike) {
//                        // 對返回結果 Bike Class 進行處理
//                        Log.d("TAG", bike.getSno() + bike.getSna());
//
//                    }
//
//                    @Override
//                    public void onError(@org.jetbrains.annotations.NotNull Throwable e) {
//                        // 出現 Error 的處理
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        // 完成後的處理
//
//                    }
//                });
    }


}