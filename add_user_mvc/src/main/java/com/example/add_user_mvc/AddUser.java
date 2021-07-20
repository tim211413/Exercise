package com.example.add_user_mvc;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.add_user_mvc.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddUser extends AppCompatActivity {
    EditText et_user_name, et_user_phone;
    Button btn_add;

    int count = 0;
    JSONArray jsonArrayInWrite = new JSONArray();

    User user;

    private static final String FILENAME = "jsonFile.json";

    //ReadFile readFile = new ReadFile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_user_phone = (EditText) findViewById(R.id.et_user_phone);

        btn_add = (Button) findViewById(R.id.btn_add);

        //readFile.readFile(FILENAME);
        readFile(FILENAME);

        btn_add.setOnClickListener(btnOnClick);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // 返回鍵的監聽器 (包含將資料寫入)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            writeFile(jsonArrayInWrite.toString());

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // 加入按鈕的監聽器
    private View.OnClickListener btnOnClick = new View.OnClickListener() {
        String userName = "";
        String userPhone = "";

        @Override
        public void onClick(View v) {
            userName = et_user_name.getText().toString();
            userPhone = et_user_phone.getText().toString();

            if (userName.equals("") || userPhone.equals("")) {
                Toast.makeText(getApplicationContext(), "名稱跟電話都必須輸入！", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("TAG", "in else" + userName + " : " + userPhone);
                user = new User(userName, userPhone);

                addJsonArray(user.getUserName(), user.getUserPhone());

                Log.d("TAG", user.getUserName() + " : " + user.getUserPhone());

                clearWord();
            }

        }
    };

    //讀資料
    public void readFile(String fileName) {

        try (FileInputStream fin = openFileInput(fileName);
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

                        addJsonArray(user.getUserName(), user.getUserPhone());
                        //count = jsonArrayInRead.length();
                    }
                    //Log.d("TAG", "jsonArrayInRead.length(): " + count);
                }
            } while (true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    //寫資料
    public void writeFile(String writer) {
        try (FileOutputStream fileOutputStream = openFileOutput(FILENAME, MODE_PRIVATE);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

            bufferedOutputStream.write(writer.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addJsonArray(String name, String phone) {
        count++;
        try {
            Log.d("TAG", "count: " + count);
            JSONObject jsonObjectInWrite = new JSONObject();
            jsonObjectInWrite.put("userName", name);
            jsonObjectInWrite.put("userPhone", phone);
            jsonArrayInWrite.put(jsonObjectInWrite);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 清除 EditText 的內容
    public void clearWord() {
        et_user_name.setText("");
        et_user_phone.setText("");
    }

}