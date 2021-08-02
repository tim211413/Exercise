package com.example.add_user_mvp.model;

import android.content.Context;
import android.util.Log;

import com.example.add_user_mvp.util.MainApplication;
import com.example.add_user_mvp.util.UseFile;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

import static com.example.add_user_mvp.view.AddUser.FILENAME;

public class UserManager {

    @Inject
    ArrayList<User> userArrayList;

    @Inject
    Gson gson;

    @Inject
    UseFile file;

    User user;

    //ReadFileInMainActivity readFileInMainActivity = new ReadFileInMainActivity();


    public UserManager(Context context) {
        MainApplication.mainComponent.inject(this);
        loadData(context);
    }

    public ArrayList<User> getUserArrayList(Context context) {
        userArrayList.clear();
        try {
            File f = new File(FILENAME);
            if (f.exists()) {
                //openFileOutput(FILENAME, MODE_APPEND);
                Log.d("TAG", "this is onResume().openFileOutput()");
            } else {
                userArrayList = file.readFile(FILENAME, context);
                Log.d("TAG4", "Presenter: " + userArrayList.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userArrayList;
    }

    public void loadData(Context context) {
        userArrayList.clear();
        userArrayList = file.readFile(FILENAME, context);
        Log.d("TAG2", "jsonArrayInWrite: " + userArrayList);
    }

    public void updateData(String name, String phone, Context context) {
        user = new User(name, phone);
        userArrayList.add(user);
        String jsonString = gson.toJson(userArrayList);

        file.writeFile(jsonString, context);
    }
}