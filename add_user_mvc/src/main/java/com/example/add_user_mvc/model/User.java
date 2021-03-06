package com.example.add_user_mvc.model;

public class User {
    private String userName;
    private String userPhone;

    public User() {

    }

    public User(String userName, String userPhone) {
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "{" +
                "\"userName\":" + "\"" + userName + "\"" +
                ", \"userPhone\":" + "\"" + userPhone + "\"" +
                "}";
    }
}
