package com.example.cloudtypetest.domain.user;

import com.example.cloudtypetest.domain.common.BaseEntity;
import com.example.cloudtypetest.domain.room.Room;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class User extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    //username == userId
    //여러가지 시도해보다가 오류나서 일단은 username상태로 뒀습니다ㅠㅠ
    //테스트용 주석  (민기)
    @Column(name = "username", length = 50, unique = true)
    private String username;


    @Column(name = "password", length = 100)
    private String password;


    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "activated")
    private boolean activated;


    @Column(name="place")
    private String place;

    @Column(name="region")
    private String region;

    @Column(name="introduce")
    private String introduce;

    @Column(name="portfolio")
    private String portfolio;


    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
