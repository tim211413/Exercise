package com.example.add_user_mvvm.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.add_user_mvvm.model.User;
import com.example.add_user_mvvm.model.UserManager;

public class UserViewModel {
    private MutableLiveData<User> userLiveData;
    UserManager userManager = new UserManager();

    public UserViewModel() {

    }
    public UserViewModel(MutableLiveData<User> userLiveData) {
        this.userLiveData = userLiveData;
    }

    public void getUserLiveData(Context context) {
        userManager.loadData(context);
    }

    public void setUserLiveData(String name, String phone, Context context) {
        userManager.updateData(name, phone, context);
    }

}
