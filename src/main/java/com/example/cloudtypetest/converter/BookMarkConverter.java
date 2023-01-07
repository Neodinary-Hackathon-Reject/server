package com.example.cloudtypetest.converter;

import com.example.cloudtypetest.domain.user.BookMark;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.web.dto.bookmark.BookMarkRes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookMarkConverter {

    public static BookMarkRes.BookMarkDto toBookMarkDto(BookMark bookMark) {
        return BookMarkRes.BookMarkDto.builder()
                .createdAt(LocalDateTime.now())
                .fromId(bookMark.getFrom().getId())
                .toId(bookMark.getTo().getId())
                .build();
    }

    public static BookMarkRes.BookMarkingDto toBookMarkingDto(User user) {
        return BookMarkRes.BookMarkingDto
                .builder()
                .userId(user.getId())
                .address(user.getRegion())
                .job(user.getJob().getJobName())
                .nickName(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .tendencyList(user.getUserTendencyList())
                .build();
    }

    public static BookMarkRes.BookMarkingUserListDto toBookMarkingUserListDto(List<User> toList) {
        List<BookMarkRes.BookMarkingDto> bookMarkingDtoList = new ArrayList<>();
        for(User user : toList) {
            bookMarkingDtoList.add(BookMarkConverter.toBookMarkingDto(user));
        }
        return BookMarkRes.BookMarkingUserListDto.builder()
                .bookMarkingDtoList(bookMarkingDtoList)
                .build();
    }
}
