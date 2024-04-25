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
    private String basicSal;
    @Column
    private String overtimeAllowance;
    @Column
    private String mealAllowance;
    @Column
    private String pension;
    @Column
    private String healthInsurance;
    @Column
    private String employmentInsurance;
    @Column
    private String incomeTax;
    @Column
    private String netSalary;
    @Column
    private String emailContent;
}
