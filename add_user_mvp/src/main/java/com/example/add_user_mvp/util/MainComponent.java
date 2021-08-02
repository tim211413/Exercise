package com.example.add_user_mvp.util;

import com.example.add_user_mvp.model.UserManager;
import com.example.add_user_mvp.presenter.AddUserPresenter;
import com.example.add_user_mvp.presenter.MainActivityPresenter;
import com.example.add_user_mvp.view.AddUser;
import com.example.add_user_mvp.view.MainActivity;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(UserManager userManager);
    void inject(UseFile useFile);
    void inject(AddUserPresenter addUserPresenter);
    void inject(MainActivityPresenter mainActivityPresenter);
    void inject(AddUser addUser);
}
