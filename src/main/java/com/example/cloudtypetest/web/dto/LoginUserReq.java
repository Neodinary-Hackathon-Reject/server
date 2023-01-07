package com.example.cloudtypetest.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserReq {

    private String username;

    private String password;
}
