package com.example.cloudtypetest.web.controller;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponse;
import com.example.cloudtypetest.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TokenProvider tokenProvider;
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

    @GetMapping("/test/jwt")
    public BaseResponse<String> jwtTest(){
        Long userId = tokenProvider.getUserIdx();
        return new BaseResponse<>("유저 아이디값:" + userId);
    }
}
