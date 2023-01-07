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
        private String nickName;
        private String address;
        private String job;
        private List<String> tendencyList;
    }
}
