package com.example.chapter07_client.entity;

public class Contact {
    public static String name;
    public static String phone;
    public static String email;

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
