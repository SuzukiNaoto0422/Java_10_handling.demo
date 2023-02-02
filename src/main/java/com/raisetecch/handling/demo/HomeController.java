package com.raisetecch.handling.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login/{id}/{name}")
    public String loginResult(@RequestParam(name = "id", required = false) Integer id,
                              @RequestParam(name = "name", required = false) String name, Model model) {
        if (id == null || name == null) {
            model.addAttribute("message", "ログインに失敗しました");
            return "loginResult";
        }
        Optional<User> user = userService.userLogin(id, name);
        if (user.isPresent()) {
            model.addAttribute("message", "ログインに成功しました");
        } else {
            model.addAttribute("message", "ログインに失敗しました");
        }
        return "loginResult";
    }

}
