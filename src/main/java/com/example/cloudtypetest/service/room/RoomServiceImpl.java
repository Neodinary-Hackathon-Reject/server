package com.example.cloudtypetest.service.room;

import com.example.cloudtypetest.converter.RoomConverter;
import com.example.cloudtypetest.domain.enums.RoomRequestStatus;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomInfo;
import com.example.cloudtypetest.domain.room.RoomUser;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.repository.RoomRepository;
import com.example.cloudtypetest.repository.RoomUserRepository;
import com.example.cloudtypetest.web.dto.room.RoomReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;

    @Transactional
    public Room createRoom(User user, RoomReq.CreateRoom createRoomDto) {
        Room room = RoomConverter.toRoomEntity(createRoomDto, user);
        Room createdRoom = roomRepository.save(room);
        return createdRoom;
    }

    @Override
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
}
