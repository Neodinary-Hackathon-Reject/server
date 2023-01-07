package com.example.cloudtypetest.web.controller.bookmark;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponse;
import com.example.cloudtypetest.converter.BookMarkConverter;
import com.example.cloudtypetest.domain.user.BookMark;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.jwt.TokenProvider;
import com.example.cloudtypetest.service.bookmark.BookMarkService;
import com.example.cloudtypetest.util.UserGetter;
import com.example.cloudtypetest.web.dto.bookmark.BookMarkRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookMarkRestController {

    private final BookMarkService bookMarkService;
    private final UserGetter userGetter;
    private final TokenProvider tokenProvider;

    @PostMapping("/bookmark/{userId}")
    public BaseResponse<BookMarkRes.BookMarkDto> bookMarking(@PathVariable(name = "userId") Long userId) {
        User loginUser = userGetter.getUserById(tokenProvider.getUserIdx());
        try {
            BookMark createdBookMark = bookMarkService.bookmark(loginUser, userId);
            return new BaseResponse<>(BookMarkConverter.toBookMarkDto(createdBookMark));
        } catch (BaseException exception) {
            return new BaseResponse(exception.getStatus());
        }
    }

    @GetMapping("/bookmark")
    public BaseResponse<BookMarkRes.BookMarkingUserListDto> bookMarkingUser() {
        User loginUser = userGetter.getUserById(tokenProvider.getUserIdx());
        List<User> toList = bookMarkService.findByFrom(loginUser);
        return new BaseResponse<>(BookMarkConverter.toBookMarkingUserListDto(toList));
    }
}
