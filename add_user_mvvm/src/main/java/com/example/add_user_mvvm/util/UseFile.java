package com.example.add_user_mvvm.util;


import android.content.Context;

import com.example.add_user_mvvm.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.add_user_mvvm.view.AddUser.FILENAME;

public class UseFile {

    public UseFile() {
        MainApp.mainComponent.inject(this);
    }

    @Inject
    ArrayList<User> users;

    @Inject
    Gson gson;

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
                    users = gson.fromJson(new String(buffer, 0, flag), new TypeToken<List<User>>() {}.getType());
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
