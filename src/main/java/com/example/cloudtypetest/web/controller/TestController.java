package com.example.cloudtypetest.web.controller;

import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.jwt.auth.AuthUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/")
    public String main() {
        return "hello cloudType";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/auth/test")
    public String authTest(@AuthUser User user) {
        System.out.println("auth test user : " + user.toString() + " userName : " + user.getUsername());
        return user.getUsername();
    }
}
