package com.example.cloudtypetest.domain.room;

import com.example.cloudtypetest.domain.Contest;
import com.example.cloudtypetest.domain.common.BaseEntity;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.domain.enums.RoomStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @ManyToOne
    private User headUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="room_info_id")
    private RoomInfo roominfo;

    // todo : 룸상태(모집완료, 모집중, 등등..)
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;
}
