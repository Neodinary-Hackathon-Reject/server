package com.example.cloudtypetest.service.room;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomUser;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.web.dto.room.RoomReq;

import java.util.List;

public interface RoomService {
    Room createRoom(User user, RoomReq.CreateRoom createRoomDto);

    RoomUser applyRoom(User user, Long roomId) throws BaseException;

    List<RoomUser> getPendingRoomUserList(User headUser, Long roomId) throws BaseException;

    String confirmRequest(User headUser, RoomReq.ConfirmUser confirmUserDto) throws BaseException;

    List<Room> findByContest(Long contestId) throws BaseException;
}
