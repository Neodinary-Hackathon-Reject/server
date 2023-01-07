package com.example.cloudtypetest.service;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponseStatus;
import com.example.cloudtypetest.domain.user.*;
import com.example.cloudtypetest.jwt.JwtFilter;
import com.example.cloudtypetest.jwt.TokenProvider;
import com.example.cloudtypetest.repository.JobRepository;
import com.example.cloudtypetest.repository.KeywordRepository;
import com.example.cloudtypetest.repository.TendencyRepository;
import com.example.cloudtypetest.repository.UserRepository;
import com.example.cloudtypetest.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JobRepository jobRepository;
    private final TendencyRepository tenancyRepository;
    private final KeywordRepository keywordRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public TokenRes login(LoginUserReq loginUserReq) throws BaseException {
        // TODO: 여기서 아이디 찾기로 회원 유무 확인

        if(!checkUserId(loginUserReq.getUsername())){
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }




        User user=userRepository.findByUsername(loginUserReq.getUsername());
        Long userId = user.getId();


        if(!passwordEncoder.matches(loginUserReq.getPassword(),user.getPassword())){
            throw new BaseException(BaseResponseStatus.NOT_CORRECT_PASSWORD);
        }



        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserReq.getUsername(), loginUserReq.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = tokenProvider.createToken(userId); //user인덱스로 토큰 생성

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);


        //반환 값 아이디 추가
        return new TokenRes(userId,jwt);
    }

    public TokenRes signup(PostUserReq postUserReq) throws BaseException {
        // TODO: 아이디 중복확인

            Authority authority = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();

            Job job = jobRepository.getOne(postUserReq.getJob());


            User user = User.builder()
                    .username(postUserReq.getUsername())
                    .password(passwordEncoder.encode(postUserReq.getPassword()))
                    .nickname(postUserReq.getNickname())
                    .job(job)
                    .region(postUserReq.getRegion())
                    .place(postUserReq.getPlace())
                    .introduce(postUserReq.getPlace())
                    .portfolio(postUserReq.getPortfolio())
                    .authorities(Collections.singleton(authority))
                    .activated(true)
                    .build();

            Long userId = userRepository.save(user).getId();

            String jwt = tokenProvider.createToken(userId);

            return new TokenRes(userId, jwt);

    }

    public boolean checkNickName(String nickName) {
        return userRepository.existsByNickname(nickName);
    }

    public boolean checkUserId(String userId) {
        return userRepository.existsByUsername(userId);
    }

    public User findUserById(Long userId){
        return userRepository.getOne(userId);
    }

    public void postKeyword(User user, List<KeywordList> keywordList) {

        for (KeywordList list : keywordList) {

            Keyword keyword = Keyword.builder()
                    .content(list.getKeyword())
                    .user(user)
                    .build();
            keywordRepository.save(keyword);
        }
    }

    public void postTendency(User user, List<TendencyList> tendencyList) {
        for (TendencyList list : tendencyList) {
            Tendency tendency = Tendency.builder()
                    .content(list.getTendency())
                    .user(user)
                    .build();
            tenancyRepository.save(tendency);
        }


    }
}
