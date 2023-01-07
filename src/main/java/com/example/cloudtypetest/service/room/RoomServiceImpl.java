package com.example.cloudtypetest.service.room;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.base.BaseResponseStatus;
import com.example.cloudtypetest.converter.RoomConverter;
import com.example.cloudtypetest.domain.Contest;
import com.example.cloudtypetest.domain.enums.RoomRequestStatus;
import com.example.cloudtypetest.domain.enums.RoomStatus;
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
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;

    private final UserRepository userRepository;

    private final ContestRepository contestRepository;

    @Transactional
    public Room createRoom(User user, RoomReq.CreateRoom createRoomDto) throws BaseException {
        Optional<Contest> optionalContest = contestRepository.findById(createRoomDto.getContestId());
        if(optionalContest.isPresent()) {
            Room room = RoomConverter.toRoomEntity(createRoomDto, user, optionalContest.get());
            Room createdRoom = roomRepository.save(room);
            RoomUser roomUser = RoomUser.builder()
                    .room(createdRoom)
                    .user(user)
                    .roomRequestStatus(RoomRequestStatus.ACCEPT) // 첫 사용자는 헤드 유저로서 ACCEPT임.
                    .build();
            roomUserRepository.save(roomUser);
            return createdRoom;
        }
        throw new BaseException(BaseResponseStatus.NOT_EXIST_CONTEST);
    }

    @Transactional
    public RoomUser applyRoom(User user, Long roomId) throws BaseException {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if(optionalRoom.isPresent()) {
            RoomUser roomUser = RoomUser.builder()
                    .user(user)
                    .room(optionalRoom.get())
                    .roomRequestStatus(RoomRequestStatus.PENDING)
                    .build();
            RoomUser createdRoomUser = roomUserRepository.save(roomUser);
            return createdRoomUser;
        }
        throw new BaseException(BaseResponseStatus.NOT_EXIST_ROOM);
    }


    public List<RoomUser> getPendingRoomUserList(User headUser, Long roomId) throws BaseException  {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if(optionalRoom.isPresent()) {
            List<RoomUser> roomUserList = roomUserRepository.findByRoomAndRoomRequestStatus(optionalRoom.get(), RoomRequestStatus.PENDING);
            return roomUserList;
        }
        throw new BaseException(BaseResponseStatus.NOT_EXIST_ROOM);
    }

    @Transactional
    public String confirmRequest(User headUser, RoomReq.ConfirmUser confirmUserDto) throws BaseException {
        // todo : 헤드 유저의 권한인지 체크 필요, 나중으로 미루기
        Optional<Room> OptionalRoom = roomRepository.findById(confirmUserDto.getRoomId());
        Optional<User> OptionalUser = userRepository.findById(confirmUserDto.getUserId());

        if(OptionalRoom.isPresent() && OptionalUser.isPresent()) {
            Optional<RoomUser> OptionalRoomUser = roomUserRepository.findByRoomAndUser(OptionalRoom.get(), OptionalUser.get());
            if(OptionalRoomUser.isPresent()) {
                if(confirmUserDto.getAcceptStatus().equals("ACCEPT")) { // 나중에 enum으로 리팩토링
                    OptionalRoomUser.get().setRoomRequestStatus(RoomRequestStatus.ACCEPT);
                    return "ACCEPT";
                }
                roomUserRepository.delete(OptionalRoomUser.get());
                return "REJECT";
            }
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }
        throw new BaseException(BaseResponseStatus.NOT_EXIST_ROOM);
    }

    @Transactional
    public List<Room> findByContest(Long contestId) throws BaseException {
        Optional<Contest> optionalContest = contestRepository.findById(contestId);
        if(optionalContest.isPresent()) {
            List<Room> roomList = roomRepository.findByContest(optionalContest.get());
            for(Room room : roomList) {
                long currentUserCount = roomUserRepository.findByRoomAndRoomRequestStatus(room, RoomRequestStatus.ACCEPT).size();
                Integer currentUserCountBoxing = Integer.valueOf((int)currentUserCount);
                room.getRoomInfo().setCurrentUserCount(currentUserCountBoxing);
                if(currentUserCountBoxing == room.getRoomInfo().getMaxUserCount()) {
                    room.setRoomStatus(RoomStatus.COMPLETE);
                }
            }
            return roomList;
        }
        throw new BaseException(BaseResponseStatus.NOT_EXIST_CONTEST);
    }

}
