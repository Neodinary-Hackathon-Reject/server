package com.example.cloudtypetest.domain.room;

import com.example.cloudtypetest.domain.common.BaseEntity;
import com.example.cloudtypetest.domain.user.Job;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfo extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Integer maxUserCount;

    @Transient
    private Integer currentUserCount;

    @Column
    @ElementCollection
    private List<String> roomJobList;

    @Column
    @ElementCollection
    private List<String> roomTendencyList;
}
