package com.example.iocexample;

import com.example.iocexample.core.IOCContext;
import com.example.iocexample.impl.UserService;

public class CustomIOCExample {



    public static void main(String[] args) {

        UserService userService = IOCContext.getBean(UserService.class);
        userService.printNme();
//        System.out.println("userService = " + userService);

    }

}
