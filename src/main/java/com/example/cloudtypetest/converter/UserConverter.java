package com.example.cloudtypetest.converter;

import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.web.dto.user.UserRes;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static UserRes.MateDto toMateDto(User user) {
        return UserRes.MateDto.builder()
                .userId(user.getId())
                .profileImageUrl(user.getProfileImageUrl())
                .nickName(user.getNickname())
                .address(user.getRegion())
                .tendencyList(user.getUserTendencyList())
                .job(user.getJob().getJobName())
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

    public static UserRes.MateDetailDto toMateDetailListDto(UserRes.UserDetailDto userDetailDto, UserRes.ReviewDetailDto reviewDetailDto, String completeProject) {
        return UserRes.MateDetailDto.builder().userId(userDetailDto.getUserId())
                .nickname(userDetailDto.getNickname())
                .region(userDetailDto.getRegion())
                .review(reviewDetailDto.getReview())
                .portfolio(userDetailDto.getPortfolio())
                .keywordList(userDetailDto.getKeywordList())
                .feedBackList(reviewDetailDto.getFeedBackList())
                .completeProject(completeProject)
                .build();
    }

    public static UserRes.UserDetailDto toUserDetailDto(User user) {
        return UserRes.UserDetailDto.builder().
                userId(user.getId()).
                nickname(user.getNickname()).
                region(user.getRegion()).
                keywordList(user.getUserKeywordList()).
                profileImageUrl(user.getProfileImageUrl()).
                portfolio(user.getPortfolio()).build();
    }

    public static UserRes.ReviewDetailDto toMateDetailDto(UserRes.ReviewDetailDto reviewDetailDto) {
        return UserRes.ReviewDetailDto.builder().
                review(reviewDetailDto.getReview())
                .feedBackList(reviewDetailDto.getFeedBackList()).build();
    }
}
