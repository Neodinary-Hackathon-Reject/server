package com.example.cloudtypetest.web.dto.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class RoomRes {
    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class CreateRoom {
        private Long createdRoomId;
        private LocalDateTime createdAt;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ApplyRoom {
        private Long userId;
        private Long roomId;
        private LocalDateTime createdAt;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class RequestUserList {
        private List<RoomRes.RequestUser> requestUserList;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class RequestUser {
        private Long userId;
        private String profileImageUrl;
        private String nickName;
        private String contestName;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ConfirmUser {
        private String confirmStatus;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class RoomInfoDto {
        private String field;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserDto {
        private Long userId;
        private String profileImageUrl;
        private String nickName;
        private String job;
        private List<String> tendencyList;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserListDto {
        List<RoomRes.UserDto> userDtoList;
    }
}
