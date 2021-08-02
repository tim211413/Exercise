package com.example.add_user_mvvm.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.add_user_mvvm.R;
import com.example.add_user_mvvm.util.MainApp;
import com.example.add_user_mvvm.viewmodel.UserViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddUser extends AppCompatActivity {
    public static final String FILENAME = "jsonFile.json";
    @BindView(R.id.et_user_name) EditText et_user_name;
    @BindView(R.id.et_user_phone) EditText et_user_phone;

    //@BindView(R.id.btn_add) Button btn_add;

    UserViewModel userViewModel;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        //context = getApplicationContext();
        ButterKnife.bind(this);
        MainApp.mainComponent.inject(this);

        userViewModel = new UserViewModel(context);

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

    // 清除 EditText 的內容
    public void clearWord() {
        et_user_name.setText("");
        et_user_phone.setText("");
    }

    // 加入按鈕的監聽器
    @OnClick(R.id.btn_add)
    public void onClick(View view) {
        String userName = "";
        String userPhone = "";

        userName = et_user_name.getText().toString();
        userPhone = et_user_phone.getText().toString();

        if (userName.equals("") || userPhone.equals("")) {
            Toast.makeText(context, "名稱跟電話都必須輸入！", Toast.LENGTH_SHORT).show();
        } else {
            userViewModel.setUserLiveData(userName, userPhone, context);
        }
        clearWord();
    }

}