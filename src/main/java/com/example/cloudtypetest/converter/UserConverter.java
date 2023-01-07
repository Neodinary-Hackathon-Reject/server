package com.example.cloudtypetest.converter;

import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.web.dto.user.UserRes;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static UserRes.MateDto toMateDto(User user) {
        return UserRes.MateDto.builder()
                .job("Developer")
                .name("피터")
                .build();
    }

    public static UserRes.MateListDto toMateListDto(List<User> userList) {
        List<UserRes.MateDto> mateDtoList = new ArrayList<>();
        for(User user : userList) {
            mateDtoList.add(UserConverter.toMateDto(user));
        }
        return UserRes.MateListDto.builder()
                .mateList(mateDtoList)
                .build();
    }

}
