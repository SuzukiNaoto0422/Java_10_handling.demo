package com.raisetecch.handling.demo.entity;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class User {

        private int id;

        @NotEmpty
        private String name;

        private int age;

        public User() {}

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(age, user.age);
        }

        @Override
        public int hashCode() {
        return Objects.hash(id, name, age);
        }

}
