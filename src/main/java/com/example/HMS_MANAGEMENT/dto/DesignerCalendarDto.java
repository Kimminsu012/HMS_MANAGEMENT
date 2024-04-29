package com.example.HMS_MANAGEMENT.dto;

import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class DesignerCalendarDto {

    private Long id; // 이벤트 ID
    private String title; // 이벤트 제목
    private String start; // 이벤트 시작 시간
    private String end; // 이벤트 종료 시간
    private boolean allDay; // 종일 이벤트 여부
    private String eventType; // 이벤트 유형

    private String name;
    private String whoAmI;


    public static DesignerCalendarDto of(DesignerCalendarEntity calendar){
        DesignerCalendarDto dto = new DesignerCalendarDto();
        dto.setTitle(calendar.getTitle());
        dto.setId(calendar.getId());
        dto.setStart(calendar.getStart().toString());
        dto.setEnd(calendar.getEnd().toString());
        dto.setAllDay(calendar.isAllDay());
        dto.setEventType(calendar.getEventType());
        dto.setName(calendar.getName());
        dto.setWhoAmI(calendar.getWhoAmI());
        return dto;
    }
}