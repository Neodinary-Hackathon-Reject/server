package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestRepository extends JpaRepository<Contest, Long> {
    List<Contest> findAll();
}
