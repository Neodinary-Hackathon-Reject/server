package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.user.Tendency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TendencyRepository extends JpaRepository<Tendency, Long> {
}