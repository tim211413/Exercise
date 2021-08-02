package com.example.add_user_mvp.util;

import android.content.Context;

import com.example.add_user_mvp.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFileInMainActivity {
    User user;
    ArrayList<User> userArrayList = new ArrayList<>();

    //讀資料
    public ArrayList readFile(String fileName, Context context) {

        try (FileInputStream fin = context.openFileInput(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {
            byte[] buffer = new byte[1024];
            do {
                int flag = bufferedInputStream.read(buffer);
                if (flag == -1) {
                    break;
                } else {
                    userArrayList.clear();
                    JsonArray jsonArray = JsonParser.parseString(new String(buffer, 0, flag)).getAsJsonArray();
                    Gson gson = new Gson();
                    for (JsonElement userArray : jsonArray) {
                        user = gson.fromJson(userArray, User.class);
                        userArrayList.add(user);
                    }
                }
            } while (true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userArrayList;
    }
}
