package com.example.cloudtypetest.domain.room;

import com.example.cloudtypetest.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

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

}
