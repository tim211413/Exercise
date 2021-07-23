package com.example.add_user_mvp.presenter;

import com.example.add_user_mvp.model.User;
import com.example.add_user_mvp.model.UserManager;
import com.example.add_user_mvp.view.MainView;

import java.util.ArrayList;

public class MainActivityPresenter {
    private MainView mainView;
    UserManager userManager = new UserManager();

    public MainActivityPresenter(MainView mainView) {
        this.mainView = mainView;
        setUserArrayList();
    }

    public ArrayList getUserArrayList() {
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.addAll(userManager.getUserArrayList());
        return userArrayList;
    }

    public void setUserArrayList() {
        mainView.reFresh();
    }
}