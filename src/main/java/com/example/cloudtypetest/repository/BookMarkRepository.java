package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.user.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
}
