package com.smlyk.demo.dto;

import java.io.Serializable;

/**
 * @author yekai
 */
public class User implements Serializable{

    private String name;

    private int age;

    private String addr;

    public User(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }
}
