package com.example.cloudtypetest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TokenRes {
    private Long userId; //user 인덱스
    private String accessToken;
}
