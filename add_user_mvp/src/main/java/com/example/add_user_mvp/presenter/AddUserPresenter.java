package com.example.add_user_mvp.presenter;

import android.content.Context;

import com.example.add_user_mvp.model.UserManager;
import com.example.add_user_mvp.util.MainApplication;

import javax.inject.Inject;

public class AddUserPresenter {

    @Inject
    UserManager userManager;

    public AddUserPresenter(Context context) {
        //userManager = new UserManager(context);
        MainApplication.mainComponent.inject(this);
    }

    public void putUserToFile(String name, String phone, Context context) {
        userManager.updateData(name, phone, context);
    }

}
