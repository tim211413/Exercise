package com.example.add_user_mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.add_user_mvp.model.User;
import com.example.add_user_mvp.util.JsonObjectAdd;
import com.example.add_user_mvp.util.UseFile;
import com.example.add_user_mvp.view.AddUserView;

import org.json.JSONArray;

import static com.example.add_user_mvp.view.AddUser.FILENAME;

public class AddUserPresenter {
    User user;
    private AddUserView addUserView;
    JSONArray jsonArrayInWrite;

    JsonObjectAdd jsonObjectAdd;
    UseFile file = new UseFile();

    public AddUserPresenter(AddUserView addUserView) {
        this.addUserView = addUserView;
    }

    public void putUserToFile(String name, String phone, Context context) {
        if (name.equals("") || phone.equals("")) {

        } else {
            user = new User(name, phone);

            jsonObjectAdd = new JsonObjectAdd();

            jsonArrayInWrite.put(jsonObjectAdd.addJsonObject(user.getUserName(), user.getUserPhone()));
            Log.d("TAG2", jsonArrayInWrite.toString());
            file.writeFile(jsonArrayInWrite.toString(), context);

            addUserView.clearWord();
        }
    }

    public void onCreate(Context context) {
        jsonArrayInWrite = new JSONArray();
        jsonArrayInWrite = (file.readFile(FILENAME, context));
        Log.d("TAG2", "jsonArrayInWrite: " + jsonArrayInWrite);
    }


    public void back() {
        addUserView.back();
    }

}
