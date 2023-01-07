package com.example.cloudtypetest.util;

import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGetter {

    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
