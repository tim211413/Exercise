package com.example.mvp_text.presenter;

import android.util.Log;

import com.example.mvp_text.model.MainModel;
import com.example.mvp_text.view.MainView;

public class MainPresenter {
    private MainView mainView;
    private MainModel mainModel;

    public MainPresenter(MainView mainView, MainModel mainModel) {
        this.mainView = mainView;
        this.mainModel = mainModel;
    }

    public void onCreate() {
        Log.d("P", "onCreate");
        mainView.setContentView();
    }

    public void onloginclick(String account, String pass) {
        Log.d("P", "onloginclick");
        mainModel.checkaccount(account, pass);
        mainView.clearEdittext();
        mainView.toastmsg(mainModel.checksuccessaccountmsg());
    }
}
