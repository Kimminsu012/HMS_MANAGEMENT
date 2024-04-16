package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SalesEntity {

    @Id
    @Column(name = "salesId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 시술 id

    @Column
    private String cut; // 시술


    @Column
    private String type; // 커트종류

    @Column
    private Integer cost; // 가격

}