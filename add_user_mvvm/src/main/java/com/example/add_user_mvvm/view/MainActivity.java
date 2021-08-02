package com.example.add_user_mvvm.view;

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

import com.example.add_user_mvvm.R;
import com.example.add_user_mvvm.model.User;
import com.example.add_user_mvvm.util.MainApp;
import com.example.add_user_mvvm.viewmodel.UserArrayListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  {
    @BindView(R.id.fab) FloatingActionButton fab;
    //FloatingActionButton fab;

    @Inject
    Context context;

    //@BindView(R.id.recyclerView) RecyclerView recyclerView;
    RecyclerView recyclerView;

    MyAdapter myAdapter;

    @Inject
    ArrayList<User> userArrayList;

    UserArrayListViewModel userArrayListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //context = getApplicationContext();
        ButterKnife.bind(this);

        MainApp.mainComponent.inject(this);
        //component.inject(this);

        userArrayListViewModel = new ViewModelProvider(this).get(UserArrayListViewModel.class);
        userArrayListViewModel.getCurrentArrayList(context).observe(this, userListUpdateObserver);
        Log.d("TAG7", userArrayListViewModel.getCurrentArrayList(context).toString());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        myAdapter = new MyAdapter(userArrayList);
        recyclerView.setAdapter(myAdapter);

        // FAB
        //fab = findViewById(R.id.fab);
        //fab.setOnClickListener(fabOnClick);
    }

    @Override
    protected void onResume() {
        userArrayListViewModel.refreshArrayList(context);
        //Log.d("TAG7", userArrayListViewModel.getCurrentArrayList(context).toString());
        //Log.d("TAG7", "hello");
        super.onResume();
    }

    Observer<ArrayList<User>> userListUpdateObserver = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(ArrayList<User> userArrayList) {
            myAdapter.setUserArrayList(userArrayList);
            myAdapter.notifyDataSetChanged();
        }
    };

    // 跳轉至增加使用者畫面的按鈕
    @OnClick(R.id.fab)
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddUser.class);
        startActivity(intent);
    }
}