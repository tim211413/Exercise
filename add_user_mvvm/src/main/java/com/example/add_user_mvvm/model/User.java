package com.example.add_user_mvvm.model;

public class User {
    private String userName;
    private String userPhone;

    public User(String userName, String userPhone) {
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
