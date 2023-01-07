package com.example.cloudtypetest.converter;

import com.example.cloudtypetest.domain.Contest;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.web.dto.contest.ContestRes;

import java.util.ArrayList;
import java.util.List;

public class ContestConverter {

    public static ContestRes.ContestDto toContestDto(Contest contest) {
        return ContestRes.ContestDto.builder()
                .build();
    }

    public static ContestRes.ContestListDto toContestListDto(List<Contest> contestList) {
        List<ContestRes.ContestDto> contestDtoList = new ArrayList<>();
        for(Contest contest : contestList) {
            contestDtoList.add(ContestConverter.toContestDto(contest));
        }
        return ContestRes.ContestListDto.builder()
                .contestDtoList(contestDtoList)
                .build();
    }

    public static ContestRes.RoomDto toRoomDto(Room room) {
        return ContestRes.RoomDto.builder()
                .roomId(room.getId())
                .currentUserCount(room.getRoomInfo().getCurrentUserCount())
                .maxUserCount(room.getRoomInfo().getMaxUserCount())
                .tendencyList(room.getRoomInfo().getRoomTendencyList())
                .jobList(room.getRoomInfo().getRoomJobList())
                .build();
    }

    public static ContestRes.RoomListDto toRoomListDto(List<Room> roomList) {
        List<ContestRes.RoomDto> roomDtoList = new ArrayList<>();
        for(Room room : roomList) {
            roomDtoList.add(ContestConverter.toRoomDto(room));
        }
        return ContestRes.RoomListDto.builder()
                .roomDtoList(roomDtoList)
                .build();
    }
}
