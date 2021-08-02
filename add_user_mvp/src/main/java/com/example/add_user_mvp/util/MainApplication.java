package com.example.add_user_mvp.util;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    public static MainComponent mainComponent;
    Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();

        mainComponent = DaggerMainComponent
                .builder()
                .mainModule(new MainModule(this, context))
                .build();

        super.onCreate();
    }
}
