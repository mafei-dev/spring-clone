package com.example.iocexample.impl;

import com.example.iocexample.annotation.Autowired;
import com.example.iocexample.annotation.Component;

@Component(name = "UserService")
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserService() {
    }

    public void printNme() {
        userDao.getUser();
    }
}
