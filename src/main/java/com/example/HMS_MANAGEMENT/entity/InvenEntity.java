package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class InvenEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 제품 코드

    @Column
    private Integer itemNm; // 제품 이름

    @Column
    private Integer count; // 제품 수량

    @Column
    private String idCalss; // 제품 분류

    @Column
    private Integer itemL; // 제품 용량
}
