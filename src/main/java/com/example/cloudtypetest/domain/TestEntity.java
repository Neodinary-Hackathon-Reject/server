package com.example.cloudtypetest.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestEntity {

    @Id @GeneratedValue
    Long id;

    private String name;
}
