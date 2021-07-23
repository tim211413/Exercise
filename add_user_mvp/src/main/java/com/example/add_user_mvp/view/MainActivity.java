package com.example.add_user_mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.add_user_mvp.R;
import com.example.add_user_mvp.model.MyAdapter;
import com.example.add_user_mvp.model.User;
import com.example.add_user_mvp.presenter.MainActivityPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView{
    FloatingActionButton fab;

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    ArrayList<User> userArrayList = new ArrayList<>();

    MainActivityPresenter mainPresenter;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // FAB
        fab = findViewById(R.id.fab);
        mainPresenter = new MainActivityPresenter(this);
    }

    @Override
    protected void onResume() {

        //Log.d("TAG3", mainPresenter.getUserArrayList(FILENAME, context).toString());

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        reFresh();
        super.onResume();
    }

    public void fabOnClick(View view) {
        mainPresenter.startAddUser();
    }


    @Override
    public void reFresh() {
        userArrayList = mainPresenter.getUserArrayList(context);

        myAdapter = new MyAdapter(userArrayList);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void startAddUserActivity() {
        Intent intent = new Intent(MainActivity.this, AddUser.class);
        startActivity(intent);
    }

}