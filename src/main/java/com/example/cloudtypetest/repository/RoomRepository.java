package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.Contest;
import com.example.cloudtypetest.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByContest(Contest contest);
}
