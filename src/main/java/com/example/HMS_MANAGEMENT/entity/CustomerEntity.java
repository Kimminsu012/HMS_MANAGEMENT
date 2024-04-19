package com.example.HMS_MANAGEMENT.entity;

import com.example.HMS_MANAGEMENT.dto.SalesDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class CustomerEntity {

    @Id
    @Column(name = "customerId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name; // 이름

    @Column
    private LocalDate firstVisit; // 첫 방문일

    @Column
    private String tel; // 전화번호

    @Column
    private String record; // 이용기록

    @Column
    private String frequentDesigner; // 자주찾는 디자이너

    @Column
    private int cusCost;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesId")
    private SalesEntity sales;

}



//@Query(" select sum(c.cusCost) from CustomerEntity c where c.firstVisit= :d ")
// int totalCustomCost(LocalDate d);