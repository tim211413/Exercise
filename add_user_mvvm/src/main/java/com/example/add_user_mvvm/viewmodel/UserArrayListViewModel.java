package com.example.add_user_mvvm.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.add_user_mvvm.model.User;
import com.example.add_user_mvvm.model.UserManager;
import com.example.add_user_mvvm.util.MainApp;

import java.util.ArrayList;

import javax.inject.Inject;

public class UserArrayListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<User>> userLiveData;

    @Inject
    UserManager userManager;

    @Inject
    public UserArrayListViewModel() {
        MainApp.mainComponent.inject(this);
        userLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<User>> getCurrentArrayList(Context context) {
        userLiveData.setValue(userManager.getUserArrayList(context));
        return userLiveData;
    }

    public void refreshArrayList(Context context) {
        getCurrentArrayList(context);
    }

}
