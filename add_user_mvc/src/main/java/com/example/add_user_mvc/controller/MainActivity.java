package com.example.add_user_mvc.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.add_user_mvc.R;
import com.example.add_user_mvc.model.User;
import com.example.add_user_mvc.util.ReadFileInMainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    ReadFileInMainActivity readFileInMainActivity = new ReadFileInMainActivity();

    ArrayList<User> userArrayList = new ArrayList<>();

    private static final String FILENAME = "jsonFile.json";

    Context context;

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // FAB
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(fabOnClick);
    }

    @Override
    protected void onResume() {
        try {
            File f = new File("data/data/com.example.add_user_mvc/jsonFile.json");
            if (f.exists()) {
                openFileOutput(FILENAME, MODE_APPEND);
                Log.d("TAG", "this is onResume().openFileOutput()");
            } else {
                userArrayList = readFileInMainActivity.readFile(FILENAME, context);
                Log.d("TAG", "this is onResume().readFile()");
            }
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter = new MyAdapter(userArrayList);
        recyclerView.setAdapter(myAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
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

}