package com.example.chapter05.enity;

public class LoginInfo {
    public int id;  // 用户编号
    public String phone;
    public String password;
    public boolean remember = false;

    public LoginInfo(String phone, String password, boolean remember) {
        this.phone = phone;
        this.password = password;
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", remember=" + remember +
                '}';
    }

    public LoginInfo() {

    }


}
