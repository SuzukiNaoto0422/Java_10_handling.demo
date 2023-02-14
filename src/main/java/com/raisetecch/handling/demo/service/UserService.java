package com.raisetecch.handling.demo.service;

import com.raisetecch.handling.demo.entity.User;
import com.raisetecch.handling.demo.entity.UserForm;
import com.raisetecch.handling.demo.exception.ResourceNotFoundException;
import com.raisetecch.handling.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

        private final UserMapper userMapper;

        @Autowired
        public UserService(UserMapper userMapper) {
            this.userMapper = userMapper;
        }

        public User findUser(int id) {
            Optional<User> user = this.userMapper.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new ResourceNotFoundException("resource not found");
            }
        }

        public UserForm entryUser(String name, Integer age) {
            if (name == null || name.isEmpty() || !name.matches("^[A-Za-z].*")) {//アルファベットで始まらない場合エラーを返す
                throw new IllegalArgumentException("Name must not be empty or null or alphabet.");
            }
            if (age == null || age <= 0) {
                throw new IllegalArgumentException("Age must not be empty or null or positive.");
            }
            UserForm form = new UserForm(name, age);
            userMapper.registrationUserByName(name, age);
            return form;
        }

        public User deleteUser(int id) {
            User userDelete = userMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
            userMapper.deleteById(id);
            return userDelete;
        }

        public User updateUser(int id, String name) {
            User userUpdate = userMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
            userMapper.updateNameById(id, name);
            return userUpdate;
        }

        public User userLogin(Integer id, String name) {
            if (id == null || name == null || name.isEmpty()) {
                throw new ResourceNotFoundException("user not found");
            }
            Optional<User> user = this.userMapper.findByIdAndName(id, name);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new ResourceNotFoundException("user not found");
            }
        }
}
