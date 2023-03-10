package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

//    @EntityGraph(attributePaths = "authorities")
//    Optional<User> findOneWithAuthoritiesById(Long userId);


    Optional<User> findById(Long id);

    User findByUsername(String username);

    User findByUsernameEquals(String username);

    //User findByid(Long userId);

    boolean existsByUsername(String userId);

    boolean existsByNickname(String nickname);



    //Optional<User> findOneWithAuthoritiesByEmail(String username);
}
