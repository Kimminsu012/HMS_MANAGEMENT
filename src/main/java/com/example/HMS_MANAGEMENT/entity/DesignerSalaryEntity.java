package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter @Getter
public class DesignerSalaryEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 디자이너 번호

    @Column
    private String DesignerNm; // 디자이너 이름

    @Column
    private LocalDate salaryDate; // 월급날

    @Column
    private Integer salary; // 월급


}
