package com.example.add_user_mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.add_user_mvp.R;
import com.example.add_user_mvp.presenter.AddUserPresenter;

public class AddUser extends AppCompatActivity implements AddUserView {
    public static final String FILENAME = "jsonFile.json";
    EditText et_user_name, et_user_phone;
    Button btn_add;

    Context context;

    AddUserPresenter addUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        context = getApplicationContext();

        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_user_phone = (EditText) findViewById(R.id.et_user_phone);

        btn_add = (Button) findViewById(R.id.btn_add);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        addUserPresenter = new AddUserPresenter(this);

        addUserPresenter.onCreate(context);
    }

    // 加入按鈕的監聽器
    @Override
    public void onClick(View v) {
        String userName = "";
        String userPhone = "";

        userName = et_user_name.getText().toString();
        userPhone = et_user_phone.getText().toString();

        addUserPresenter.putUserToFile(userName, userPhone, context);
    }

    // 清除 EditText 的內容
    @Override
    public void clearWord() {
        et_user_name.setText("");
        et_user_phone.setText("");
    }

    // 返回鍵的監聽器 (包含將資料寫入)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            addUserPresenter.back();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void back() {
        finish();
    }
}