package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.user.BookMark;
import com.example.cloudtypetest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    @Query("select b from BookMark b where b.from = :fromuser")
    List<BookMark> findByFrom(@Param(value = "fromuser") User fromuser);
}
