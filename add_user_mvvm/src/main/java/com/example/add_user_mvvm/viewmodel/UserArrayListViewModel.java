package com.example.add_user_mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.add_user_mvvm.model.User;

import java.util.ArrayList;

public class UserArrayListViewModel extends ViewModel {
    MutableLiveData<ArrayList<User>> userLiveData;
    ArrayList<User> userArrayList;

    public UserArrayListViewModel() {
        userLiveData = new MutableLiveData<>();
        init();
    }

    public void init() {
        userLiveData.setValue(userArrayList);
    }

    public MutableLiveData<ArrayList<User>> getMyUser() {
        return userLiveData;
    }
    public void setMyUser(ArrayList<User> userArrayList) {
        this.userLiveData.setValue(userArrayList);
    }
}
