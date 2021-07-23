package com.example.add_user_mvvm.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.add_user_mvvm.R;
import com.example.add_user_mvvm.viewmodel.UserViewModel;


public class AddUser extends AppCompatActivity {
    public static final String FILENAME = "jsonFile.json";
    EditText et_user_name, et_user_phone;
    Button btn_add;

    UserViewModel userViewModel = new UserViewModel();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        context = getApplicationContext();

        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_user_phone = (EditText) findViewById(R.id.et_user_phone);

        btn_add = (Button) findViewById(R.id.btn_add);

        btn_add.setOnClickListener(btnOnClick);

        userViewModel.getUserLiveData(context);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    // 返回鍵的監聽器 (包含將資料寫入)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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

            userViewModel.setUserLiveData(userName, userPhone, context);

            clearWord();
        }
    };

    // 清除 EditText 的內容
    public void clearWord() {
        et_user_name.setText("");
        et_user_phone.setText("");
    }

}