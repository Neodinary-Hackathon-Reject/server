package com.example.cloudtypetest.converter;

import com.example.cloudtypetest.domain.user.BookMark;
import com.example.cloudtypetest.web.dto.bookmark.BookMarkRes;

import java.time.LocalDateTime;

public class BookMarkConverter {

    public static BookMarkRes.BookMarkDto toBookMarkDto(BookMark bookMark) {
        return BookMarkRes.BookMarkDto.builder()
                .createdAt(LocalDateTime.now())
                .fromId(bookMark.getFrom().getId())
                .toId(bookMark.getTo().getId())
                .build();
    }
}
