package com.raisetecch.handling.demo.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserForm {

    @NotEmpty
    private String name;

    @NotNull
    private  Integer age;

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
