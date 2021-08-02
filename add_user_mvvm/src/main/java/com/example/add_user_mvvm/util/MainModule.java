package com.example.add_user_mvvm.util;

import android.app.Application;
import android.content.Context;

import com.example.add_user_mvvm.model.User;
import com.example.add_user_mvvm.model.UserManager;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module public class MainModule {
    Application mApplication;
    Context context;
    public MainModule(Application application, Context context) {
        mApplication = application;
        this.context = context;
    }

    @Singleton
    @Provides
    UserManager provideUserManager() {
        return new UserManager(context);
    }

    @Singleton
    @Provides
    UseFile provideUseFile() {
        return new UseFile();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    ArrayList<User> provideArrayList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

}
