package com.auth.service.controller;

import com.auth.service.entity.User;
import com.auth.service.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/registration")
    public void addUser(@RequestBody @Valid User user){
        userService.addUser(user);
    }
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody @Valid Map<String, String> user){
        return Map.of("token", userService.loginUser(user));
    }

}
