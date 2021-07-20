package com.example.livedatatext;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Demo1Activity";
    private DemoViewModel mDemoViewModel;
    private Button btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = findViewById(R.id.btn_1);
        mDemoViewModel = ViewModelProviders.of(this).get(DemoViewModel.class); //獲取ViewModel,讓ViewModel與此activity綁定
        mDemoViewModel.getMyString().observe(this, new Observer<String>() { //註冊觀察者
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged:值有變化=" + s);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDemoViewModel.setMyString("測試"); //用手動按鍵點擊改變值
            }
        });
    }
}