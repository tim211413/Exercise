package com.example.exercise;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.HandroidLoggerAdapter;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String TAG = "MainActivity";

    String txt = "123456"; // get a logger instance by name

    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);

//
//        // SLF4J
//        Logger LOG = LoggerFactory.getLogger(MainActivity.class);
//        LOG.info("hello world");


        int a = 12;
        int b = 15;
        int c = a + b;

        Log.d(TAG, "c = a + b = " + c);
        textView.setText(txt);

        YourClass.foo();
    }
    public static class YourClass {
        public static void foo() {
            HandroidLoggerAdapter.DEBUG = BuildConfig.DEBUG;
            HandroidLoggerAdapter.ANDROID_API_LEVEL = Build.VERSION.SDK_INT;
            HandroidLoggerAdapter.APP_NAME = "Exercise";

            log.error("Something failed", new RuntimeException("something"));
        }
    }


}