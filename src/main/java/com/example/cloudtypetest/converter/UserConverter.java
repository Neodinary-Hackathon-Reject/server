package com.example.cloudtypetest.converter;

import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.web.dto.user.UserRes;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static UserRes.MateDto toMateDto(User user) {
        return UserRes.MateDto.builder()
                .userId(user.getId())
                .nickName(user.getNickname())
                .address(user.getRegion())
                .tendencyList(new ArrayList<>())
                .job("Developer") // todo : 테이블 바뀌면 바꿔주기
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
