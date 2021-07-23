package com.example.add_user_mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.add_user_mvp.model.User;
import com.example.add_user_mvp.util.ReadFileInMainActivity;
import com.example.add_user_mvp.view.MainView;

import java.io.File;
import java.util.ArrayList;

import static com.example.add_user_mvp.view.AddUser.FILENAME;

public class MainActivityPresenter {
    private MainView mainView;
    ArrayList<User> userArrayList = new ArrayList<>();
    ReadFileInMainActivity readFileInMainActivity = new ReadFileInMainActivity();

    public MainActivityPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public ArrayList getUserArrayList(Context context) {
        try {
            File f = new File(FILENAME);
            if (f.exists()) {
                //openFileOutput(FILENAME, MODE_APPEND);
                Log.d("TAG", "this is onResume().openFileOutput()");
            } else {
                userArrayList = readFileInMainActivity.readFile(FILENAME, context);
                Log.d("TAG3", "Presenter: " + userArrayList.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return userArrayList;
    }

        public void startAddUser () {
            mainView.startAddUserActivity();
        }

//        public void getArrayList () {
//            mainView.reFresh();
//        }
}