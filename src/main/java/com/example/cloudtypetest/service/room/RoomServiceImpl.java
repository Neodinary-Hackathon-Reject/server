package com.example.cloudtypetest.service.room;

import com.example.cloudtypetest.converter.RoomConverter;
import com.example.cloudtypetest.domain.Contest;
import com.example.cloudtypetest.domain.enums.RoomRequestStatus;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomInfo;
import com.example.cloudtypetest.domain.room.RoomUser;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.repository.ContestRepository;
import com.example.cloudtypetest.repository.RoomRepository;
import com.example.cloudtypetest.repository.RoomUserRepository;
import com.example.cloudtypetest.repository.UserRepository;
import com.example.cloudtypetest.web.dto.room.RoomReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;

    private final UserRepository userRepository;

    private final ContestRepository contestRepository;

    @Transactional
    public Room createRoom(User user, RoomReq.CreateRoom createRoomDto) {
        Room room = RoomConverter.toRoomEntity(createRoomDto, user);
        Room createdRoom = roomRepository.save(room);
        RoomUser roomUser = RoomUser.builder()
                .room(createdRoom)
                .user(user)
                .roomRequestStatus(RoomRequestStatus.ACCEPT) // 첫 사용자는 헤드 유저로서 ACCEPT임.
                .build();
        roomUserRepository.save(roomUser);
        return createdRoom;
    }

    @Transactional
    public RoomUser applyRoom(User user, Long roomId) {
        Room room = roomRepository.findById(roomId).get(); // todo : 추후 존재하지 않는 방의 경우 예외처리
        RoomUser roomUser = RoomUser.builder()
                .user(user)
                .room(room)
                .roomRequestStatus(RoomRequestStatus.PENDING)
                .build();
        RoomUser createdRoomUser = roomUserRepository.save(roomUser);
        return createdRoomUser;
    }


    public List<RoomUser> getPendingRoomUserList(User headUser, Long roomId) {
        Room room = roomRepository.findById(roomId).get();
        List<RoomUser> roomUserList = roomUserRepository.findByRoomAndRoomRequestStatus(room, RoomRequestStatus.PENDING);
        return roomUserList;
    }

    @Transactional
    public String confirmRequest(User headUser, RoomReq.ConfirmUser confirmUserDto) {
        // todo : 헤드 유저의 권한인지 체크 필요, 나중으로 미루기
        Room room = roomRepository.findById(confirmUserDto.getRoomId()).get();
        User user = userRepository.findById(confirmUserDto.getUserId()).get();

        RoomUser roomUser = roomUserRepository.findByRoomAndUser(room, user).get();
        if(confirmUserDto.getAcceptStatus().equals("ACCEPT")) { // 나중에 enum으로 리팩토링
            roomUser.setRoomRequestStatus(RoomRequestStatus.ACCEPT);
            return "ACCEPT";
        }
        roomUserRepository.delete(roomUser);
        return "REJECT";
    }

    @Override
    public List<Room> findByContest(Long contestId) {
        Contest contest = contestRepository.findById(contestId).get();
        return roomRepository.findByContest(contest);
    }

}
