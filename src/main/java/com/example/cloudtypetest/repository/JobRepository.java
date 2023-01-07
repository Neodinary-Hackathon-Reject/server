package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.user.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}