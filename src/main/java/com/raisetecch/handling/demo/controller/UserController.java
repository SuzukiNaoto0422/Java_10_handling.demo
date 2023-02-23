package com.raisetecch.handling.demo.controller;

import com.raisetecch.handling.demo.exception.IllegalArgumentException;
import com.raisetecch.handling.demo.exception.ResourceNotFoundException;
import com.raisetecch.handling.demo.entity.User;
import com.raisetecch.handling.demo.entity.UserForm;
import com.raisetecch.handling.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/users")//dbにユーザーの登録
    public ResponseEntity<String> post(@RequestBody User user) {
        userService.entryUser(user.getName(), user.getAge());
        return ResponseEntity.ok().body("user successfully created");
    }

    @DeleteMapping("/users/{id}")//idの一致するユーザーの削除
    public ResponseEntity<Map<String,String>> delete(@PathVariable("id") Integer id) {
        boolean user = userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "user successfully deleted"));
    }

    @PatchMapping("/users/{id}")//idに対応するユーザーデータの更新
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable("id") int id,
                                                          @RequestBody UserForm updateForm) {
        User user = userService.updateUser(id, updateForm.getName());
        return ResponseEntity.ok(Map.of("message", "name successfully updated:" + user.getName()));
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

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleIllegalArgument(
            IllegalArgumentException e, HttpServletRequest request) {

        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error",HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());

        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

}
