
package com.example.HMS_MANAGEMENT.dto;

import com.example.HMS_MANAGEMENT.constent.CommuteStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class CommuteListDto {

    private Long id;

    private String name;

    private LocalTime mTime;

    private LocalTime aTime;

    private CommuteStatus commuteStatus;

    private LocalTime morningTime;

    private LocalTime afterTime;

    private LocalTime resultTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private LocalTime time;
}
