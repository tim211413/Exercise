package com.example.mvp_text.model;

import android.util.Log;

public class MainModel {

    public String msg="";
    public void checkaccount(String account, String pass) {
        Log.d("M","checkaccount");
        if(account.equals("andy")){
            msg="successful";
        }
        else {msg="fail";}
    }

    public String checksuccessaccountmsg() {
        Log.d("M","checksuccessaccountmsg");
        return msg;
    }
}
