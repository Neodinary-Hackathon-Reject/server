package com.example.cloudtypetest.service.bookmark;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponseStatus;
import com.example.cloudtypetest.domain.user.BookMark;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.repository.BookMarkRepository;
import com.example.cloudtypetest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookMarkServiceImpl implements BookMarkService{
    private final BookMarkRepository bookMarkRepository;
    private final UserRepository userRepository;

    @Transactional
    public BookMark bookmark(User loginUser, Long userId) throws BaseException{
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            BookMark bookMark = BookMark.builder()
                    .from(loginUser)
                    .to(userOptional.get())
                    .build();
            BookMark createdBookMark = bookMarkRepository.save(bookMark);
            return createdBookMark;
        }
        return throw BaseException(BaseResponseStatus.NOT_EXIST_USER);
    }
}
