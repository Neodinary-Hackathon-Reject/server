package com.example.cloudtypetest.web.dto.bookmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class BookMarkRes {
    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class BookMarkDto {
        private LocalDateTime createdAt;
        private Long fromId;
        private Long toId;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class BookMarkingDto {
        private Long userId;
        private String profileImageUrl;
        private String nickName;
        private String address;
        private String job;
        private List<String> tendencyList;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class BookMarkingUserListDto {
        List<BookMarkRes.BookMarkingDto> bookMarkingDtoList;
    }
}
