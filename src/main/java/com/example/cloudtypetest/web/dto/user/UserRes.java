package com.example.cloudtypetest.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserRes {

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Get{

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class GetKeywordRes {
        private String keyword;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class MateListDto {
        private List<MateDto> mateList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class MateDto {
        private Long userId;
        private String profileImageUrl;
        private String nickName;
        private String address;
        private String job;
        private List<String> tendencyList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class UserDetailDto{
        private Long userId;
        private String profileImageUrl;
        private String nickname;
        private String region;
        private String portfolio;
        private List<String> keywordList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class ReviewDetailDto {
        private String review;
        private List<String> feedBackList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class MateDetailDto {
        private Long userId;
        private String profileImageUrl;
        private String nickname;
        private String region;
        private String review;
        private String portfolio;
        private List<String> keywordList;
        private List<String> feedBackList;
    }

}
