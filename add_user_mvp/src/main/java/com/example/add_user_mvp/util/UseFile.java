package com.example.add_user_mvp.util;


import android.content.Context;

import com.example.add_user_mvp.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import static com.example.add_user_mvp.view.AddUser.FILENAME;

public class UseFile {

    public UseFile() {
        MainApplication.mainComponent.inject(this);
    }

    @Inject
    Gson gson;

    @Inject
    ArrayList<User> users;

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
                    users = gson.fromJson(new String(buffer, 0, flag), new TypeToken<ArrayList<User>>() {}.getType());
                }
            } while (true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    //寫資料
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
