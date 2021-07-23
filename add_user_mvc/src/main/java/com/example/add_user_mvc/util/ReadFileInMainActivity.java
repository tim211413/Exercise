package com.example.add_user_mvc.util;

import android.content.Context;
import android.util.Log;

import com.example.add_user_mvc.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                    JSONArray jsonArrayInRead = new JSONArray(new String(buffer, 0, flag));
                    for (int i = 0; i < jsonArrayInRead.length(); i++) {
                        JSONObject jsonObjectInRead = jsonArrayInRead.getJSONObject(i);
                        String userNameInJson = jsonObjectInRead.getString("userName");
                        String userPhoneInJson = jsonObjectInRead.getString("userPhone");

                        Log.d("TAG", "userNameInJson: " + userNameInJson);
                        Log.d("TAG", "userPhoneInJson: " + userPhoneInJson);

                        user = new User(userNameInJson, userPhoneInJson);
                        userArrayList.add(user);
                    }
                }
            } while (true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return userArrayList;
    }
}
