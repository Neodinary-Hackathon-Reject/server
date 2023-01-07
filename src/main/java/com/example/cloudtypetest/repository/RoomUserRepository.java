package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.room.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserRepository extends JpaRepository<RoomUser, Long> {
}
