package com.example.chapter06.enity;

public class User {
    public int id;  // 用户编号
    public String name; // 用户姓名
    public int age; // 用户年龄
    public long height; // 用户身高
    public long weight; // 用户体重
    public boolean married; // 用户婚姻状况

    public User(String name, int age, long height, long weight, boolean married) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.married = married;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", married=" + married +
                '}';
    }

    public User() {

    }


}
