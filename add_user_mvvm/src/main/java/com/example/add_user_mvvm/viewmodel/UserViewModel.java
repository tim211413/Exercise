package com.example.add_user_mvvm.viewmodel;

import android.content.Context;

import com.example.add_user_mvvm.model.UserManager;
import com.example.add_user_mvvm.util.MainApp;

import javax.inject.Inject;

public class UserViewModel {

    @Inject
    UserManager userManager;

    public UserViewModel(Context context) {
        //userManager = new UserManager(context);
        MainApp.mainComponent.inject(this);
    }

    public void setUserLiveData(String name, String phone, Context context) {
        userManager.updateData(name, phone, context);
    }

}
