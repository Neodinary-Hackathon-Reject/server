package com.example.cloudtypetest.web.controller;

import com.example.cloudtypetest.service.UserService;
import com.example.cloudtypetest.web.dto.LoginUserReq;
import com.example.cloudtypetest.web.dto.PostUserReq;
import com.example.cloudtypetest.web.dto.TokenRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenRes> login(@RequestBody LoginUserReq loginUserReq){

        System.out.println();
            TokenRes tokenRes = userService.login(loginUserReq);

            return new ResponseEntity<>(tokenRes, HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<TokenRes> signup(@RequestBody PostUserReq postUserReq) {
        TokenRes tokenRes = userService.signup(postUserReq);
        return new ResponseEntity<>(tokenRes, HttpStatus.OK);

    }
}
