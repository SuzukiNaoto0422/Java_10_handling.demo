package controller;

import exception.ResourceNotFoundException;
import entity.User;
import service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZonedDateTime;
import java.util.Map;

@Controller
public class loginController {

    private final UserService userService;

    @Autowired
    public loginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login") //http://localhost:8080/login
    public String login() {
        return "login";
    }

    @GetMapping("/login/{id}/{name}")
    public String loginResult(@RequestParam(name = "id", required = false) Integer id,
                              @RequestParam(name = "name", required = false) String name, Model model) {
        User user = userService.userLogin(id, name);
        model.addAttribute("message", "ログインに成功しました");
        return "loginResult";
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
