package com.raisetecch.handling.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")//dbの中のユーザーの検索
    public User getUser(@PathVariable("id") int id) {
        return userService.findUser(id);
    }

    @PostMapping("/entry/{id}/{name}")//dbにユーザーの登録
    public User postUser(@PathVariable("id") int id,
                         @PathVariable("name") String name) {
        return userService.entryUser(id,name);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleNoResourceFound(
            ResourceNotFoundException e, HttpServletRequest request) {

        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error",HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());

        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }

}
