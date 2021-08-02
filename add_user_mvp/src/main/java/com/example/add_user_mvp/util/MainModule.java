package com.example.add_user_mvp.util;

import android.app.Application;
import android.content.Context;

import com.example.add_user_mvp.model.User;
import com.example.add_user_mvp.model.UserManager;
import com.google.gson.Gson;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    Application mApplication;
    Context context;

    public MainModule(Application application, Context context) {
        mApplication = application;
        this.context = context;
    }

    @Provides
    UserManager provideUserManager() {
        return new UserManager(context);
    }

//    @Singleton
    @Provides
    UseFile provideUseFile() {
        return new UseFile();
    }

//    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

//    @Singleton
    @Provides
    ArrayList<User> provideArrayList() {
        return new ArrayList<>();
    }

    @Provides
    Context provideContext() {
        return context;
    }
}
