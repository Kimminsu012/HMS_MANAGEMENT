package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class EmailEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String designerName;
    @Column
    private String designerEmail;
    @Column
    private Integer basicSal;
    @Column
    private Integer overtimeAllowance;
    @Column
    private Integer mealAllowance;
    @Column
    private Integer pension;
    @Column
    private Integer healthInsurance;
    @Column
    private Integer employmentInsurance;
    @Column
    private Integer incomeTax;
    @Column
    private Integer netSalary;
}
