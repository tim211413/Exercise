package com.example.add_user_mvvm.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.add_user_mvvm.model.User;
import com.example.add_user_mvvm.model.UserManager;

import java.util.ArrayList;

public class UserArrayListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<User>> userLiveData;
    UserManager userManager = new UserManager();

    public UserArrayListViewModel() {
        userLiveData = new MutableLiveData<>();
    }
    public void init() {
        userManager.getUserArrayList();
    }

    public MutableLiveData<ArrayList<User>> getUserArrayList() {
        init();
        return userLiveData;
    }
    public void setUserArrayList(Context context) {
        userManager.setUserArrayList(context);
        this.userLiveData.setValue(userManager.getUserArrayList());
    }

}
