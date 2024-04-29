
package com.example.HMS_MANAGEMENT.entity;

import com.example.HMS_MANAGEMENT.constent.CommuteStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class CommuteListEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "commute_status")
    private CommuteStatus commuteStatus;

    @Column
    private String name;

    @Column
    private LocalTime mTime;

    @Column
    private LocalTime aTime;

    @Column
    private LocalTime morningTime;

    @Column
    private LocalTime afterTime;

    @Column
    private LocalTime resultTime;

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;
}
