package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.room.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {
}
