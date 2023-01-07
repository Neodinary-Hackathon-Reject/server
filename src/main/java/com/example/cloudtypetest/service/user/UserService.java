package com.example.cloudtypetest.service.user;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponseStatus;
import com.example.cloudtypetest.converter.UserConverter;
import com.example.cloudtypetest.domain.enums.RoomRequestStatus;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomUser;
import com.example.cloudtypetest.domain.user.*;
import com.example.cloudtypetest.jwt.JwtFilter;
import com.example.cloudtypetest.jwt.TokenProvider;
import com.example.cloudtypetest.repository.*;
import com.example.cloudtypetest.util.UserGetter;
import com.example.cloudtypetest.web.dto.*;
import com.example.cloudtypetest.web.dto.user.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JobRepository jobRepository;
    private final KeywordInfoRepository keywordInfoRepository;
    private final ReviewRepository reviewRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserGetter  userGetter;

    private final RoomRepository roomRepository;

    private final RoomUserRepository roomUserRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public TokenRes login(LoginUserReq loginUserReq) throws BaseException {
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

    private String getRandomProfileImageUrl() {
        List<String> urlList = List.of(
                "https://www.beginmate.com/Upload/Mate/d678fcb4-9975-42f1-b2b5-3d2e45eb2655_ThumbSmall.png",
                "https://www.beginmate.com/Upload/Mate/9f333db9-2c4a-4cee-b8e2-13920defecfe_ThumbSmall.png",
                "https://www.beginmate.com/Upload/Mate/BodyPart_92394af9-9eb0-4f87-a5a9-a0f5892f1355_ThumbSmall.png",
                "https://www.beginmate.com/Upload/Mate/c8db3de1-032d-4cfd-92ab-e4bc7649cd16_ThumbSmall.png",
                "https://www.beginmate.com/Upload/Mate/BodyPart_915053ff-a6ce-4a4d-975c-f66c21336342_ThumbSmall.png",
                "https://www.beginmate.com/Upload/Mate/e4dc6374-94f3-4ebf-aec4-492006ad0afd_ThumbSmall.png",
                "https://www.beginmate.com/Upload/Mate/78ed3f60-1e63-4821-bcef-cc5ad84ff21c_ThumbSmall.png",
                "https://www.beginmate.com/Upload/Mate/359b547f-214d-4fe4-8e0f-344767200552_ThumbSmall.png"
        );
        Random random = new Random();
        int randomIndex = random.nextInt(urlList.size());
        return urlList.get(randomIndex);
    }

    public TokenRes signup(PostUserReq postUserReq) throws BaseException {


            Authority authority = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();

            Job job = jobRepository.getOne(postUserReq.getJob());


            User user = User.builder()
                    .username(postUserReq.getUsername())
                    .profileImageUrl(getRandomProfileImageUrl())
                    .password(passwordEncoder.encode(postUserReq.getPassword()))
                    .nickname(postUserReq.getNickname())
                    .job(job)
                    .region(postUserReq.getRegion())
                    .place(postUserReq.getPlace())
                    .introduce(postUserReq.getPlace())
                    .portfolio(postUserReq.getPortfolio())
                    .userKeywordList(postUserReq.getKeywordList())
                    .userTendencyList(postUserReq.getTendencyList())
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




    public List<UserRes.GetKeywordRes> getKeywordList(String keyword) {
        List<KeywordInfoRepository.GetKeywordList> keywordInfoResult = keywordInfoRepository.findByContentContains(keyword);
        List<UserRes.GetKeywordRes> result = new ArrayList<>();
        keywordInfoResult.forEach(keywordInfo -> result.add(
                new UserRes.GetKeywordRes(keywordInfo.getContent())));

        return result;
    }

    public List<User> findByRoomAndRoomRequestStatus(Long roomId, RoomRequestStatus accept) throws BaseException {

        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if(optionalRoom.isPresent()) {
            List<RoomUser> roomUserList = roomUserRepository.findByRoomAndRoomRequestStatus(optionalRoom.get(), RoomRequestStatus.ACCEPT);
            List<User> userList = roomUserList.stream()
                    .map(roomUser -> userRepository.findById(roomUser.getUser().getId()).get())
                    .collect(Collectors.toList());
            return userList;
        }
        throw new BaseException(BaseResponseStatus.NOT_EXIST_ROOM);
    }

    public UserRes.UserDetailDto getUserDetail(Long userId) {
        User user=userRepository.getOne(userId);
        return UserConverter.toUserDetailDto(user);

    }


    public UserRes.ReviewDetailDto getReviewDetail(Long userId) {

        User user = userGetter.getUserById(userId);
        List<Review> reviewList=reviewRepository.findByTargetUser(user);
        List<String> resultList=new ArrayList<>();


        UserRes.ReviewDetailDto reviewDetailDto=null;
        if(reviewList.size()==0) {
            for (Review review : reviewList) {
                resultList.addAll(review.getFeedBackList());
            }
            reviewDetailDto = new UserRes.ReviewDetailDto(null, resultList);
        }
        else {
            for (Review review : reviewList) {
                resultList.addAll(review.getFeedBackList());
            }
            reviewDetailDto = new UserRes.ReviewDetailDto(reviewList.get(0).getReview(), resultList);
        }



        return UserConverter.toMateDetailDto(reviewDetailDto);
    }
}
