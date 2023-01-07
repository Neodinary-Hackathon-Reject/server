package com.example.cloudtypetest.service.bookmark;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.domain.user.BookMark;
import com.example.cloudtypetest.domain.user.User;

import java.util.List;

public interface BookMarkService {
    BookMark bookmark(User loginUser, Long userId) throws BaseException;

    List<User> findByFrom(User loginUser);
}
