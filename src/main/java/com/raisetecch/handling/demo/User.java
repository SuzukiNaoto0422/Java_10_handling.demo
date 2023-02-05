package com.raisetecch.handling.demo;

import jakarta.validation.constraints.NotEmpty;

public class User {

        @NotEmpty
        private int id;

        @NotEmpty
        private String name;

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

}
