package com.example.iocexample.impl;

import com.example.iocexample.annotation.Component;

@Component(name = "UserDao")
public class UserDao {
    public void getUser(){
        System.out.println("user is kalhara");
    }
}
