package com.example.cloudtypetest.web.dto.contest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ContestRes {

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ContestDto {
        private Long contestId;
        private String imgUrl;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ContestListDto {
        private List<ContestDto> contestDtoList;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class RoomDto {
        private Long roomId;
        private List<String> jobList;
        private List<String> tendencyList;
        private Integer maxUserCount;
        private Integer currentUserCount;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class RoomListDto {
        private List<ContestRes.RoomDto> roomDtoList;
    }
}
