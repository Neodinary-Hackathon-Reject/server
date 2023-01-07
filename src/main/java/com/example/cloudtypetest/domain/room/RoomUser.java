package com.example.cloudtypetest.domain.room;


import com.example.cloudtypetest.domain.common.BaseEntity;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.domain.enums.RoomRequestStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomUser extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // todo : 룸과 유저가 맺어졌는지 상태
    @Enumerated(EnumType.STRING)
    private RoomRequestStatus roomRequestStatus;

}
