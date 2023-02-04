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

        public User entryUser(String name) {
            if (name.matches("^[^A-Za-z].*")) {//アルファベットで始まる場合エラーを返す
                throw new ResourceNotFoundException("Name must not be empty or null.");
            }
            User user = new User(name);
            userMapper.registrationUserByName(name);
            return user;
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
