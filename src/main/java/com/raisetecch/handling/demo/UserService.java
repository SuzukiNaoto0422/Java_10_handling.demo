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

        public UserForm entryUser(String name) {
            if (name.matches("^[^A-Za-z].*")) {//アルファベットで始まらない場合エラーを返す
                throw new ResourceNotFoundException("Name must not be empty or null or alphabet.");
            }
            UserForm form = new UserForm(name);
            userMapper.registrationUserByName(name);
            return form;
        }

        public User deleteUser(int id) {
            User userDelete = userMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
            userMapper.deleteById(id);
            return userDelete;
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
