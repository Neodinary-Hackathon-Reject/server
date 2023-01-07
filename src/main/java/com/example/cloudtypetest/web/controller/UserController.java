package com.example.cloudtypetest.web.controller;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponse;
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

import static com.example.cloudtypetest.base.BaseResponseStatus.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public BaseResponse<TokenRes> login(@RequestBody LoginUserReq loginUserReq){
        if(loginUserReq.getUsername()==null){
            return new BaseResponse<>(USERS_EMPTY_USER_ID);
        }
        if(loginUserReq.getPassword()==null){
            return new BaseResponse<>(USERS_EMPTY_USER_PASSWORD);
        }

        try {
            TokenRes tokenRes = userService.login(loginUserReq);
            return new BaseResponse<>(tokenRes);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/signup")
    public BaseResponse<TokenRes> signup(@RequestBody PostUserReq postUserReq) {
        try {
            if (userService.checkUserId(postUserReq.getUsername())) {
                return new BaseResponse<>(USERS_EXISTS_ID);
            }
            if (userService.checkNickName(postUserReq.getNickname())) {
                return new BaseResponse<>(USERS_EXISTS_NICKNAME);
            }
            return new BaseResponse<>(userService.signup(postUserReq));
        }catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }

    }
}
