package com.example.cloudtypetest.domain.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordInfo {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="content")
    private String content;
}
