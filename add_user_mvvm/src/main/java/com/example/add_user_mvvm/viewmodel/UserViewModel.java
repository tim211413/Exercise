package com.example.add_user_mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.add_user_mvvm.model.User;

public class UserViewModel extends ViewModel {
    private MutableLiveData<User> myUser = new MutableLiveData<>();

    public MutableLiveData<User> getMyUser() {
        return myUser;
    }
    public void setMyUser(User user) {
        this.myUser.setValue(user);
    }
}
