package com.raisetecch.handling.demo;

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

        public User entryUser(Integer id, String name) {
            if (id == null || id <= 0) {//idがnull or 0以下の時
                throw new ResourceNotFoundException("Invalid ID: " + id);
            }

            if (name == null || name.trim().isEmpty()) {//名前が未入力の場合
                throw new ResourceNotFoundException("Invalid name: " + name);
            }

            User existingUser = userMapper.findUserByID(id);
            if (existingUser != null) {//既存のidと入力が重複した場合
                throw new ResourceNotFoundException("ID already exists: " + id);
            }

            int result = userMapper.insertUser(id, name);
            if (result == 1) {//挿入されるレコードが１つかどうか
                System.out.println("registration completed");
                return new User(id, name);
            } else {
                throw new ResourceNotFoundException("could not register");
            }
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
