package com.example.add_user_mvc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.add_user_mvc.model.MyAdapter;
import com.example.add_user_mvc.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    User user;

    ArrayList<User> userArrayList = new ArrayList<>();

    private static final String FILENAME = "jsonFile.json";

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FAB
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(fabOnClick);
    }

    @Override
    protected void onResume() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter = new MyAdapter(userArrayList);
        recyclerView.setAdapter(myAdapter);

        try {
            File f = new File(FILENAME);
            if (f.exists()) {
                openFileOutput(FILENAME, MODE_APPEND);
                Log.d("TAG", "this is onResume().openFileOutput()");
            } else {
                readFile(FILENAME);
                Log.d("TAG", "this is onResume().readFile()");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //readFile(FILENAME);
        //myAdapter.notifyDataSetChanged();
        Log.d("TAG", "this is onResume()");
        super.onResume();
    }

    private View.OnClickListener fabOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddUser.class);
            startActivity(intent);
        }
    };

    //讀資料
    public void readFile(String fileName) {
        userArrayList.clear();
        try (FileInputStream fin = openFileInput(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)){

            byte[] buffer = new byte[1024];
            do {
                int flag = bufferedInputStream.read(buffer);
                if (flag == -1) {
                    break;
                } else {
                    JSONArray jsonArrayInRead = new JSONArray(new String(buffer, 0, flag));
                    Log.d("TAG", "jsonArray: " + jsonArrayInRead.toString());
                    Log.d("TAG", "jsonArray: " + jsonArrayInRead.length());
                    for (int i = 0; i < jsonArrayInRead.length(); i++) {
                        JSONObject jsonObjectInRead = jsonArrayInRead.getJSONObject(i);
                        String userNameInJson = jsonObjectInRead.getString("userName");
                        String userPhoneInJson = jsonObjectInRead.getString("userPhone");

                        Log.d("TAG", "userNameInJson: " + userNameInJson);
                        Log.d("TAG", "userPhoneInJson: " + userPhoneInJson);

                        user = new User(userNameInJson, userPhoneInJson);

                        userArrayList.add(user);

                        Log.d("TAG", "arrayList.toString: " + userArrayList.toString());
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
    }

}