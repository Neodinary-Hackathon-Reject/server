package com.example.cloudtypetest.service.bookmark;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.domain.user.BookMark;
import com.example.cloudtypetest.domain.user.User;

public interface BookMarkService {
    BookMark bookmark(User loginUser, Long userId) throws BaseException;
}
