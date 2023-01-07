package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.user.Review;
import com.example.cloudtypetest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByTargetUser(User user);

}