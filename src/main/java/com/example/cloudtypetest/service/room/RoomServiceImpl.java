package com.example.cloudtypetest.service.room;

import com.example.cloudtypetest.converter.RoomConverter;
import com.example.cloudtypetest.domain.room.Room;
import com.example.cloudtypetest.domain.room.RoomInfo;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.repository.RoomRepository;
import com.example.cloudtypetest.web.dto.room.RoomReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public Room createRoom(User user, RoomReq.CreateRoom createRoomDto) {
        Room room = RoomConverter.toRoomEntity(createRoomDto, user);
        Room createdRoom = roomRepository.save(room);
        return room;
    }
}
