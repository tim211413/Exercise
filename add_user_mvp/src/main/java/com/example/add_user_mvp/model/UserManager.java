package com.example.add_user_mvp.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.add_user_mvp.util.JsonObjectAdd;
import com.example.add_user_mvp.util.ReadFileInMainActivity;
import com.example.add_user_mvp.util.UseFile;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

import static com.example.add_user_mvp.view.AddUser.FILENAME;

public class UserManager {
    private ArrayList<User> userArrayList;
    User user;

    ReadFileInMainActivity readFileInMainActivity = new ReadFileInMainActivity();

    JSONArray jsonArrayInWrite;

    JsonObjectAdd jsonObjectAdd;
    UseFile file = new UseFile();

    public UserManager() {

    }

    public UserManager(Context context) {
        setUserArrayList(context);
        loadData(context);
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(Context context) {

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
    }

    public void loadData(Context context) {
        jsonArrayInWrite = new JSONArray();
        jsonArrayInWrite = (file.readFile(FILENAME, context));
        Log.d("TAG2", "jsonArrayInWrite: " + jsonArrayInWrite);
    }

    public void updateData(String name, String phone, Context context) {

        if (name.equals("") || phone.equals("")) {
            Toast.makeText(context, "名稱跟電話都必須輸入！", Toast.LENGTH_SHORT).show();
        } else {
            user = new User(name, phone);

            jsonObjectAdd = new JsonObjectAdd();

            jsonArrayInWrite.put(jsonObjectAdd.addJsonObject(user.getUserName(), user.getUserPhone()));
            Log.d("TAG2", jsonArrayInWrite.toString());
            file.writeFile(jsonArrayInWrite.toString(), context);
        }
    }
}