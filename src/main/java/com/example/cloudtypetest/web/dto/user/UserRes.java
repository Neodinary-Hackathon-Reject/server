package com.example.cloudtypetest.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserRes {

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Get{

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class GetKeywordRes {
        private String ab;
    }
}
