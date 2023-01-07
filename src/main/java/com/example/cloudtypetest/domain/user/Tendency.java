package com.example.cloudtypetest.domain.user;

import com.example.cloudtypetest.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tendency extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
