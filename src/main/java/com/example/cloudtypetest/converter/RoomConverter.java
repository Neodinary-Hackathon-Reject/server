package com.example.cloudtypetest.converter;

import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomInfo;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.web.dto.room.RoomReq;
import com.example.cloudtypetest.web.dto.room.RoomRes;

import java.time.LocalDateTime;

public class RoomConverter {

    // todo : 구현 필요
    public static Room toRoomEntity(RoomReq.CreateRoom createRoomDto, User user) {
        // 여기서 RoomInfo까지 만들어서 셋팅해서 던져주기
        // headUser랑, Contest셋팅해주기 contestId로 ㄱ

        return Room.builder().build();
    }

    public static RoomRes.CreateRoom toCreateRoomDto(Room room) {
        return RoomRes.CreateRoom.builder()
                .createdAt(LocalDateTime.now())
                .createdRoomId(room.getId())
                .build();
    }
}
