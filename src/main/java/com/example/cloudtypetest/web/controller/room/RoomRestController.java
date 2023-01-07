package com.example.cloudtypetest.web.controller.room;

import com.example.cloudtypetest.base.BaseResponse;
import com.example.cloudtypetest.web.dto.room.RoomRes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomRestController {

    @PostMapping("")
    public BaseResponse<RoomRes.CreateRoom> creatRoom() {
        return null;
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
