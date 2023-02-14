package com.raisetecch.handling.demo.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class User {

        @NotEmpty
        private int id;

        @NotEmpty
        private String name;

        @NotNull
        private Integer age;

        public User() {}

        public User(String name) {
            this.name = name;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
