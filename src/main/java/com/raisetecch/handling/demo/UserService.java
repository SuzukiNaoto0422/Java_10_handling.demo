package com.raisetecch.handling.demo;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

        private final UserMapper userMapper;

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
}
