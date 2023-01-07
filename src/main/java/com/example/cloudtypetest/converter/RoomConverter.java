package com.example.cloudtypetest.converter;

import com.example.cloudtypetest.domain.enums.RoomStatus;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomInfo;
import com.example.cloudtypetest.domain.room.RoomUser;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.web.dto.room.RoomReq;
import com.example.cloudtypetest.web.dto.room.RoomRes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomConverter {

    // todo : 구현 필요
    public static Room toRoomEntity(RoomReq.CreateRoom createRoomDto, User user) {
        RoomInfo roomInfo = RoomInfo.builder()
                .roomJobList(createRoomDto.getJobList())
                .roomTendencyList(createRoomDto.getTendencyList())
                .maxUserCount(createRoomDto.getMaxUserCount())
                .build();

        // todo : Contest셋팅해주기 contestId
        return Room.builder()
                 // .contest()
                .roomInfo(roomInfo)
                .roomStatus(RoomStatus.RECRUITING)
                .headUser(user)
                .build();
    }

    public static RoomRes.CreateRoom toCreateRoomDto(Room room) {
        return RoomRes.CreateRoom.builder()
                .createdAt(LocalDateTime.now())
                .createdRoomId(room.getId())
                .build();
    }

    public static RoomRes.ApplyRoom toApplyRoomDto(RoomUser roomUser) {
        return RoomRes.ApplyRoom.builder()

                .build();
    }

    public static RoomRes.RequestUser toRequestUserDto(RoomUser roomUser) {
        return RoomRes.RequestUser.builder()
                .build();
    }

    public static RoomRes.RequestUserList toRequestUserListDto(List<RoomUser> roomUserList) {
        List<RoomRes.RequestUser> requestUserList = new ArrayList<>();
        for(RoomUser roomUser : roomUserList) {
            requestUserList.add(toRequestUserDto(roomUser));
        }
        return RoomRes.RequestUserList.builder()
                .requestUserList(requestUserList)
                .build();
    }

    public static RoomRes.ConfirmUser toConfirmUserDto(String confirmStatus) {
        return RoomRes.ConfirmUser.builder()
                .confirmStatus(confirmStatus)
                .build();
    }
}
