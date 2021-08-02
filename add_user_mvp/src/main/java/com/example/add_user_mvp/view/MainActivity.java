package com.example.add_user_mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.add_user_mvp.R;
import com.example.add_user_mvp.model.User;
import com.example.add_user_mvp.presenter.MainActivityPresenter;
import com.example.add_user_mvp.util.MainApplication;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {
    FloatingActionButton fab;

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    MainActivityPresenter mainPresenter;

    @Inject
    Context context;

    @Inject
    ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //context = getApplicationContext();
        MainApplication.mainComponent.inject(this);

        // FAB
        fab = findViewById(R.id.fab);

        mainPresenter = new MainActivityPresenter(this);

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter = new MyAdapter(userArrayList);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onResume() {
        mainPresenter.refresh(context);
        super.onResume();
    }

    // 重新獲得 userArrayList
    @Override
    public void reFresh(ArrayList<User> userArrayList) {
        myAdapter.setUserArrayList(userArrayList);
        myAdapter.notifyDataSetChanged();
        Log.d("TAG4", "notifyDataSetChanged, userArrayList: " + userArrayList.toString());
    }

    // 前往新增使用者畫面的按鈕
    public void fabOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddUser.class);
        startActivity(intent);
    }

}