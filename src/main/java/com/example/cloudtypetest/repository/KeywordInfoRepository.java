package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.user.KeywordInfo;
import com.example.cloudtypetest.web.dto.user.UserRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface KeywordInfoRepository extends JpaRepository<KeywordInfo, Long>{

    List<GetKeywordList> findByContentContains(String content);
    interface GetKeywordList{
        String getContent();
    }
}