package com.example.cloudtypetest.web.dto;

import com.example.cloudtypetest.domain.user.User;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUserReq {
    private String username;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String nickname;

    private Long job;

    private String place;

    private String region;

    private String introduce;

    private String portfolio;

    private List<KeywordList> keywordList;

    private List<TendencyList> tendencyList;;
    //이건 requestBody 입력할 때, 입력할 필요 없음
    private Set<AuthorityRes> authorityResSet;

    public static PostUserReq from(User user) {
        if(user == null) return null;

        return PostUserReq.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .authorityResSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityRes.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
