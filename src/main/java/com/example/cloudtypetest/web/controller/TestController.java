package com.example.cloudtypetest.web.controller;

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
}
