package com.example.cloudtypetest.web.controller.room;

import com.example.cloudtypetest.base.BaseResponse;
import com.example.cloudtypetest.converter.RoomConverter;
import com.example.cloudtypetest.domain.enums.RoomRequestStatus;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomInfo;
import com.example.cloudtypetest.domain.room.RoomUser;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.jwt.TokenProvider;
import com.example.cloudtypetest.service.room.RoomService;
import com.example.cloudtypetest.service.user.UserService;
import com.example.cloudtypetest.util.UserGetter;
import com.example.cloudtypetest.web.dto.room.RoomReq;
import com.example.cloudtypetest.web.dto.room.RoomRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomRestController {

    private final UserGetter userGetter;
    private final TokenProvider tokenProvider;

    private final RoomService roomService;

    private final UserService userService;

    // 특정 공모전에 대한 방 리스트 내려주는 API
    //

    @PostMapping("")
    public BaseResponse<RoomRes.CreateRoom> creatRoom(@RequestBody RoomReq.CreateRoom createRoomDto) {
        User loginUser = userGetter.getUserById(tokenProvider.getUserIdx());
        Room createdRoom = roomService.createRoom(loginUser, createRoomDto);
        return new BaseResponse<>(RoomConverter.toCreateRoomDto(createdRoom));
    }

    @PostMapping("/apply/{roomId}")
    public BaseResponse<RoomRes.ApplyRoom> applyRoom(@PathVariable(name = "roomId" ) Long roomId) {
        User loginUser = userGetter.getUserById(tokenProvider.getUserIdx());
        RoomUser roomUser = roomService.applyRoom(loginUser, roomId);
        return new BaseResponse<>(RoomConverter.toApplyRoomDto(roomUser));
    }

    /*
        Logic
        1. 접속한 사용자가 헤드유저로 속해있는 방들을 가져옴
        2. 해당 방의 id로 roomUser 테이블을 조회하는데 where roomUser.Status가 PENDING인 행들을 조회
        3. DTO로 변환하여 내려줌
     */
    @GetMapping("/request/{roomId}")
    public BaseResponse<RoomRes.RequestUserList> getRequestList(@PathVariable(name = "roomId" ) Long roomId) {
        User headUser = userGetter.getUserById(tokenProvider.getUserIdx());
        List<RoomUser> roomUserList = roomService.getPendingRoomUserList(headUser, roomId);
        return new BaseResponse<>(RoomConverter.toRequestUserListDto(roomUserList));
    }

    /*
        Logic
        접속한 유저가 roomId, 허락하고자 하는 userId, ACCEPT / REJECT을 보냄
    */
    @PostMapping("/confirm/request")
    public BaseResponse<RoomRes.ConfirmUser> confirmRequest(@RequestBody RoomReq.ConfirmUser confirmUserDto) {
        User headUser = userGetter.getUserById(tokenProvider.getUserIdx());
        String confirmStatus = roomService.confirmRequest(headUser, confirmUserDto);
        return new BaseResponse<>(RoomConverter.toConfirmUserDto(confirmStatus));
    }

    @GetMapping("/{roomId}/users")
    public BaseResponse<RoomRes.UserListDto> getUsersbyRoom(@PathVariable(value = "roomId") Long roomId) {
        List<User> userList = userService.findByRoomAndRoomRequestStatus(roomId, RoomRequestStatus.ACCEPT);
        return new BaseResponse<>(RoomConverter.toUserListDto(userList));
    }



    // todo : 여기 스펙이 불분명하므로 추후 작성
    @PatchMapping("/room/info")
    public BaseResponse<RoomRes.RoomInfoDto> updateRoomInfo() {
        return null;
    }

}
