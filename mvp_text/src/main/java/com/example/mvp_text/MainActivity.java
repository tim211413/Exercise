package com.example.mvp_text;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp_text.model.MainModel;
import com.example.mvp_text.presenter.MainPresenter;
import com.example.mvp_text.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter mainPresenter;
    private EditText editaccount, editpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter(this, new MainModel());
        mainPresenter.onCreate();
    }

    @Override
    public void clearEdittext() {
        Log.d("V", "clearEdittext");
        getaccountViewById().setText("");
        getpassViewByid().setText("");
    }


    @Override
    public void toastmsg(String checksuccessaccountmsg) {
        Log.d("V", "toastmsg");
        Toast.makeText(this, checksuccessaccountmsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setContentView() {
        Log.d("V", "setContentView");
        setContentView(R.layout.activity_main);
    }

    public void btn_login(View view) {
        mainPresenter.onloginclick(getaccountViewById().getText().toString(), getpassViewByid().getText().toString());
    }

    private EditText getpassViewByid() {
        return (EditText) findViewById(R.id.editText2);
    }

    private EditText getaccountViewById() {
        return (EditText) findViewById(R.id.editText);
    }
}