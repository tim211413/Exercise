package com.example.add_user_mvp.presenter;

import android.content.Context;

import com.example.add_user_mvp.model.UserManager;

public class AddUserPresenter {
    UserManager userManager;

    public AddUserPresenter(Context context) {
        userManager = new UserManager(context);
        getUserFromFile(context);
    }

    public void putUserToFile(String name, String phone, Context context) {
        userManager = new UserManager();
        userManager.updateData(name, phone, context);
    }

    public void getUserFromFile(Context context) {
        userManager.loadData(context);
    }

}
