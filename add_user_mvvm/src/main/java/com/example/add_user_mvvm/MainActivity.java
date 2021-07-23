package com.example.add_user_mvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.add_user_mvvm.model.MyAdapter;
import com.example.add_user_mvvm.model.User;
import com.example.add_user_mvvm.util.ReadFileInMainActivity;
import com.example.add_user_mvvm.viewmodel.UserArrayListViewModel;
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

    private UserArrayListViewModel userArrayListViewModel;


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
            File f = new File(FILENAME);
            if (f.exists()) {
                openFileOutput(FILENAME, MODE_APPEND);
                Log.d("TAG", "this is onResume().openFileOutput()");
            } else {
                userArrayList = readFileInMainActivity.readFile(FILENAME, context);
                Log.d("TAG", "this is onResume().readFile()");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //myAdapter.notifyDataSetChanged();
        Log.d("TAG", "this is onResume()");

        recyclerView = findViewById(R.id.recyclerView);

        userArrayListViewModel = new ViewModelProvider(this).get(UserArrayListViewModel.class);
        userArrayListViewModel.getMyUser().observe(this, userListUpdateObserver);

        myAdapter.notifyDataSetChanged();

        super.onResume();
    }


    Observer<ArrayList<User>> userListUpdateObserver = new Observer<ArrayList<User>>() {

        @Override
        public void onChanged(ArrayList<User> userArrayList) {
            userArrayList = getUserArrayList();

            myAdapter = new MyAdapter(userArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

            //myAdapter.getArrayList(userArrayList);
            recyclerView.setAdapter(myAdapter);
        }
    };

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    private View.OnClickListener fabOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddUser.class);
            startActivity(intent);
        }
    };

}