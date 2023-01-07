package com.example.cloudtypetest.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;
}
