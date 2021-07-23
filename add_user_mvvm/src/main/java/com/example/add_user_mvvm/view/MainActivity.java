package com.example.add_user_mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.add_user_mvvm.R;
import com.example.add_user_mvvm.model.User;
import com.example.add_user_mvvm.viewmodel.UserArrayListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    Context context;

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    UserArrayListViewModel userArrayListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        recyclerView = findViewById(R.id.recyclerView);

        // FAB
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(fabOnClick);
    }

    @Override
    protected void onResume() {
        userArrayListViewModel = new ViewModelProvider(this).get(UserArrayListViewModel.class);
        userArrayListViewModel.setUserArrayList(context);
        userArrayListViewModel.getUserArrayList().observe(this, userListUpdateObserver);

        super.onResume();
    }


    Observer<ArrayList<User>> userListUpdateObserver = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(ArrayList<User> userArrayList) {
            myAdapter = new MyAdapter(userArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(myAdapter);
        }
    };
    // 增加使用者的按鈕
    private View.OnClickListener fabOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddUser.class);
            startActivity(intent);
        }
    };

}