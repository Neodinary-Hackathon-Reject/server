package com.example.cloudtypetest.web.controller.room;

import com.example.cloudtypetest.base.BaseResponse;
import com.example.cloudtypetest.converter.RoomConverter;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.jwt.TokenProvider;
import com.example.cloudtypetest.service.room.RoomService;
import com.example.cloudtypetest.util.UserGetter;
import com.example.cloudtypetest.web.dto.room.RoomReq;
import com.example.cloudtypetest.web.dto.room.RoomRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomRestController {

    private final UserGetter userGetter;
    private final TokenProvider tokenProvider;

    private final RoomService roomService;

    @PostMapping("")
    public BaseResponse<RoomRes.CreateRoom> creatRoom(@RequestBody RoomReq.CreateRoom createRoomDto) {
        User loginUser = userGetter.getUserById(tokenProvider.getUserIdx());
        Room createdRoom = roomService.createRoom(loginUser, createRoomDto);
        return new BaseResponse<>(RoomConverter.toCreateRoomDto(createdRoom));
    }

    @PostMapping("/apply")
    public BaseResponse<RoomRes.ApplyRoom> applyRoom() {
        return null;
    }

    @GetMapping("/request/{roomId}")
    public BaseResponse<RoomRes.RequestUserList> getRequestList() {
        return null;
    }

    @PostMapping("/confirm/request")
    public BaseResponse<RoomRes.ConfirmUser> confirmRequest() {
        return null;
    }

    @PatchMapping("/room/info")
    public BaseResponse<RoomRes.RoomInfo> updateRoomInfo() {
        return null;
    }
}
