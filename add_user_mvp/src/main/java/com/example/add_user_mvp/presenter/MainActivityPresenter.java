package com.example.add_user_mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.add_user_mvp.model.User;
import com.example.add_user_mvp.model.UserManager;
import com.example.add_user_mvp.util.MainApplication;
import com.example.add_user_mvp.view.MainView;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivityPresenter {
    private MainView mainView;

    @Inject
    UserManager userManager;

    @Inject
    ArrayList<User> userArrayList;

    public MainActivityPresenter(MainView mainView) {
        MainApplication.mainComponent.inject(this);
        this.mainView = mainView;
    }

    public ArrayList getUserArrayList(Context context) {
        userArrayList.clear();
        userArrayList.addAll(userManager.getUserArrayList(context));
        Log.d("TAG4", "getUserArrayList in MainPresenter: " + userArrayList.toString());
        return userArrayList;
    }

    public void refresh(Context context) {
        userArrayList = getUserArrayList(context);
        Log.d("TAG4", "getUserArrayList in refresh: " + userArrayList.toString());
        mainView.reFresh(userArrayList);
    }
}