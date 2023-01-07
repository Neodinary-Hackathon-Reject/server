package com.example.cloudtypetest.web.dto.bookmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
