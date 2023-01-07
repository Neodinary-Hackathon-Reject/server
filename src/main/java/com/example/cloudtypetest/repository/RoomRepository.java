package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
