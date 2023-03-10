package com.example.cloudtypetest.domain.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="job_name")
    private String jobName;

}
