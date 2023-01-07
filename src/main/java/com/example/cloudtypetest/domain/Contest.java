package com.example.cloudtypetest.domain;

import com.example.cloudtypetest.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contest extends BaseEntity {
    @Id
    @Column
    @GeneratedValue
    private Long id;

}
