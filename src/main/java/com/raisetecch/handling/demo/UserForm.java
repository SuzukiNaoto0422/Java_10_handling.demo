package com.raisetecch.handling.demo;

import jakarta.validation.constraints.NotEmpty;

public class UserForm {

    @NotEmpty
    private String name;

    public UserForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
