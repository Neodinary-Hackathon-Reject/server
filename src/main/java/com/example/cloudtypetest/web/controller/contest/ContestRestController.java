package com.example.cloudtypetest.web.controller.contest;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponse;
import com.example.cloudtypetest.converter.ContestConverter;
import com.example.cloudtypetest.domain.Contest;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.service.contest.ContestService;
import com.example.cloudtypetest.service.room.RoomService;
import com.example.cloudtypetest.web.dto.contest.ContestRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contests")
public class ContestRestController {

    private final ContestService contestService;
    private final RoomService roomService;

    @GetMapping("")
    public BaseResponse<ContestRes.ContestListDto> getContests() {
        List<Contest> contestList = contestService.findAll();
        return new BaseResponse<>(ContestConverter.toContestListDto(contestList));
    }

    @GetMapping("/{contestId}")
    public BaseResponse<ContestRes.RoomListDto> getRooms(@PathVariable(value = "contestId")Long contestId) {
        try {
            List<Room> roomList = roomService.findByContest(contestId);
            return new BaseResponse<>(ContestConverter.toRoomListDto(roomList));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
