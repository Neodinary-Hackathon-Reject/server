package com.example.cloudtypetest.service.room;

import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomUser;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.web.dto.room.RoomReq;

public interface RoomService {
    Room createRoom(User user, RoomReq.CreateRoom createRoomDto);

    RoomUser applyRoom(User user, Long roomId);
}
