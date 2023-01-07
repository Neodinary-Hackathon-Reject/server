package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.enums.RoomRequestStatus;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomUser;
import com.example.cloudtypetest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomUserRepository extends JpaRepository<RoomUser, Long> {
    List<RoomUser> findByRoomAndRoomRequestStatus(Room room, RoomRequestStatus roomRequestStatus);

    Optional<RoomUser> findByRoomAndUser(Room room, User user);
}
