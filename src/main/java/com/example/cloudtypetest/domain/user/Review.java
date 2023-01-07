package com.example.cloudtypetest.domain.user;

import com.example.cloudtypetest.domain.common.BaseEntity;
import com.example.cloudtypetest.domain.room.Room;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="target_id")
    private User targetUser;

    @Column(name="review")
    private String review;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Column
    @ElementCollection
    private List<String> feedBackList;


    public Review(String review, String[] newFeedBackList) {
        super();
    }
}
