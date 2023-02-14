package com.raisetecch.handling.demo.entity;

import jakarta.validation.constraints.NotEmpty;

public class UserForm {

    @NotEmpty
    private String name;

    @NotEmpty
    private  int age;

    public UserForm() {
    }

    public UserForm(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
