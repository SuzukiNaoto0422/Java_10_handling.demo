package com.raisetecch.handling.demo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;


public class User {

        @Null
        private int id;

        @NotNull
        private String name;

        public User(String name) {
            this.id = id;
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

}
