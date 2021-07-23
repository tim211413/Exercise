package com.example.add_user_mvp.util;


import android.content.Context;
import android.util.Log;

import com.example.add_user_mvp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.add_user_mvp.view.AddUser.FILENAME;

public class UseFile {
    User user;
    JsonArrayAdd jsonArrayAdd;
    JSONArray jsonArray = new JSONArray();
    //讀資料
    public JSONArray readFile(String fileName, Context context) {

        try (FileInputStream fin = context.openFileInput(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {
            byte[] buffer = new byte[1024];
            do {
                int flag = bufferedInputStream.read(buffer);
                if (flag == -1) {
                    break;
                } else {
                    JSONArray jsonArrayInRead = new JSONArray(new String(buffer, 0, flag));
                    for (int i = 0; i < jsonArrayInRead.length(); i++) {
                        JSONObject jsonObjectInRead = jsonArrayInRead.getJSONObject(i);
                        String userNameInJson = jsonObjectInRead.getString("userName");
                        String userPhoneInJson = jsonObjectInRead.getString("userPhone");

                        Log.d("TAG", "userNameInJson: " + userNameInJson);
                        Log.d("TAG", "userPhoneInJson: " + userPhoneInJson);

                        user = new User(userNameInJson, userPhoneInJson);
                        jsonArrayAdd = new JsonArrayAdd();
                        jsonArray.put(jsonArrayAdd.addJsonArray(user.getUserName(), user.getUserPhone()));
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
        return jsonArray;
    }//寫資料
    public void writeFile(String writer, Context context) {
        try (FileOutputStream fileOutputStream = context.openFileOutput(FILENAME, context.MODE_PRIVATE)) {
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                bufferedOutputStream.write(writer.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
