package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class DesignerFormEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 디자이너 번호

    @Column
    private String customerNm; // 고객 이름

    @Column
    private LocalDateTime time; // 시간

    @Column
    private LocalDate date; // 날짜

    @Column
    private String cuts; // 시술

}
