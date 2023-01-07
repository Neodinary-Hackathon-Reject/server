package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
