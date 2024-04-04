package com.example.HMS_MANAGEMENT.entity;

import com.example.HMS_MANAGEMENT.constent.absence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class DesignerSchEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 디자이너 번호

    @Column
    private String DesignerNm; // 디자이너 이름

    @Column
    private LocalDate date; // 날짜

    @Column
    private absence absence; // 부재 사항

    @Column
    private LocalTime workTime; // 계획 근로시간

    @Column
    private LocalTime recordTime; // 근태 기록시간

    @Column
    private String attStatus; // 근태정보

    @Column
    private LocalTime work; // 근로시간

    @Column
    private String Remarks; // 비고
}
