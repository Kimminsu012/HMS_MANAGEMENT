package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class DesignerEntity {

    @Id
    @Column(name = "designer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 디자이너 번호

    @Column
    private String name; // 디자이너 이름

    @Column
    private String tel; // 연락처

    @Column
    private LocalDate date; // 등록일

    @Column
    private Integer sal; // 월급

    @Column
    private LocalDate salDate; // 월급날

    @OneToMany(mappedBy = "designer", cascade = CascadeType.ALL)
    private List<DayOffEntity> dayOffs = new ArrayList<>();

}
