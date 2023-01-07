package com.example.cloudtypetest.web.controller;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponse;
import com.example.cloudtypetest.converter.UserConverter;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.jwt.TokenProvider;
import com.example.cloudtypetest.service.user.UserService;
import com.example.cloudtypetest.web.dto.LoginUserReq;
import com.example.cloudtypetest.web.dto.PostUserReq;
import com.example.cloudtypetest.web.dto.TokenRes;
import com.example.cloudtypetest.web.dto.user.UserReq;
import com.example.cloudtypetest.web.dto.user.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.cloudtypetest.base.BaseResponseStatus.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenProvider tokenProvider;

    @GetMapping("")
    public BaseResponse<UserRes.MateListDto> getMate() {
        List<User> userList = userService.findAll();
        return new BaseResponse<>(UserConverter.toMateListDto(userList));
    }

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

            System.out.println(postUserReq.getTendencyList());
            TokenRes tokenRes = userService.signup(postUserReq);

            User user = userService.findUserById(tokenRes.getUserId());
            /*
            userService.postKeyword(user,postUserReq.getKeywordList());
            userService.postTendency(user,postUserReq.getTendencyList());

             */

            return new BaseResponse<>(tokenRes);
        }catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/signup/keyword")
    public BaseResponse<List<UserRes.GetKeywordRes>> getKeywordList(@RequestParam("keyword") String keyword){
        System.out.println(keyword);
        List<UserRes.GetKeywordRes> getKeywordRes = userService.getKeywordList(keyword);
        return new BaseResponse<>(getKeywordRes);
    }



    @GetMapping("/check/userId")
    public BaseResponse<String> checkUserId(@Param("userId") String userId){
        String result="";
        System.out.println(userService.checkUserId(userId));
        System.out.println(userId);
        if(userService.checkUserId(userId)){
            return new BaseResponse<>(USERS_EXISTS_ID);
        }
        else{
            result="사용 가능합니다.";
        }
        return new BaseResponse<>(result);

    }

    @GetMapping("/my_page")
    public BaseResponse<UserRes.MateDetailDto> getMyPage(){
        Long userId=tokenProvider.getUserIdx();
        UserRes.UserDetailDto userDetailDto=userService.getUserDetail(userId);
        UserRes.ReviewDetailDto reviewDetailDto=userService.getReviewDetail(userId);
        String completeProject=userService.getCompleteProject(userId);

        UserRes.MateDetailDto metaDetailDto=UserConverter.toMateDetailListDto(userDetailDto,reviewDetailDto, completeProject);

        return new BaseResponse<>(metaDetailDto);
    }

    @GetMapping("/my_room")
    public BaseResponse<String> getMyRoom(){
        return null;
    }

    @PatchMapping
    public BaseResponse<String> patchUserInfo(@RequestBody UserReq.PatchUserReq patchUserReq){

        return null;
    }

    @GetMapping("/detail/{userId}")
    public BaseResponse<UserRes.MateDetailDto> getUserMateDetail(@PathVariable("userId")Long userId){
        UserRes.UserDetailDto userDetailDto=userService.getUserDetail(userId);
        UserRes.ReviewDetailDto reviewDetailDto=userService.getReviewDetail(userId);
        String completeProject=userService.getCompleteProject(userId);

        UserRes.MateDetailDto metaDetailDto=UserConverter.toMateDetailListDto(userDetailDto,reviewDetailDto,completeProject);

        return new BaseResponse<>(metaDetailDto);
    }


    @GetMapping("/review")
    public BaseResponse<List<UserRes.ReviewListDto>> getReviewList(){
        Long userId=tokenProvider.getUserIdx();
        List<UserRes.ReviewListDto> reviewListDto=userService.getReviewList(userId);
        return new BaseResponse<>(reviewListDto);
    }





}
