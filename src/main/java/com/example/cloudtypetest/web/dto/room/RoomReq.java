package com.example.cloudtypetest.web.dto.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class RoomReq {
    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class CreateRoom {
        private Long contestId;
        private Integer maxUserCount; // 모집인원
        private List<String> jobList; // 필요직무 : 문자열 리스트
        private List<String> tendencyList;// 원하는 팀들의 성향 : 문자열 리스트
        private String details;// 참고사항
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ConfirmUser {
        private Long roomId;
        private Long userId;
        private String acceptStatus; // ACCEPT, REJECT
    }

}
