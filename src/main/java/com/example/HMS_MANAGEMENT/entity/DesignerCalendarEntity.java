package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class DesignerCalendarEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 이벤트 ID

    @Column
    private String title; // 이벤트 제목
    @Column
    private LocalDateTime start; // 이벤트 시작 시간
    @Column
    private LocalDateTime end; // 이벤트 종료 시간
    @Column
    private boolean allDay; // 종일 이벤트 여부
    @Column
    private String eventType; // 이벤트 유형
}
