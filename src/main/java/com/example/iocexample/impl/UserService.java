package com.example.iocexample;

@Bean(name = "MyBean")
public class MyBean {
    @Autowired
    private String sample;

    public MyBean() {
    }

    public void printNme(String name) {
        System.out.println("The name is: " + name);
    }
}
