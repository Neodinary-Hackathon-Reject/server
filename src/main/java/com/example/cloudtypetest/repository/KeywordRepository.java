package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.user.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}