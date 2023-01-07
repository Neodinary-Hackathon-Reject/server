package com.example.cloudtypetest.domain;

import com.example.cloudtypetest.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;

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
    private
    Long id;

    @Column(name="tittle")
    private String title;

    @Column(name="url")
    private String url;

    @Column(name="home_page")
    private String homePage;

    @Column(name="part")
    private String part;

    @Column(name="funding")
    private String funding;

    //전체 상금
    @Column(name="all_price")
    private String allPrice;

    //총 상금
    @Column(name="first_price")
    private String firstPrice;

    //시작 날짜
    @Column(name="start_date")
    private LocalDate startDate;

    //종료 날짜
    @Column(name="finish_date")
    private LocalDate finishDate;

    //상세 내용
    @Column(name="detail",columnDefinition = "TEXT")
    private String detail;

}
