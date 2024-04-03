package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class DesignerEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 디자이너 번호

    @Column
    private String name; // 디자이너 이름

    @Column
    private Integer phonNum; // 연락처

    @Column
    private Integer salary; // 월급

    @Column
    private String free; // 휴일
}
