package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.DesignerCalendarDto;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DesignerCalendarService {
    private final DesignerCalendarRepo designerCalendarRepo;

    @Autowired
    public DesignerCalendarService(DesignerCalendarRepo designerCalendarRepo){
        this.designerCalendarRepo = designerCalendarRepo;
    }

    public List<DesignerCalendarDto> getCalendarData(){
        LocalDateTime date = LocalDateTime.now();
        int yearValue = date.getYear();
        int monthValue = date.getMonthValue(); // 현재 월의 숫자 값 (1부터 12까지)
        int lastDayOfMonth = YearMonth.of(yearValue,monthValue).lengthOfMonth(); // 이번 달의 마지막 일

        LocalDateTime start = LocalDateTime.of(yearValue, monthValue, 1,0,0);
        LocalDateTime end = LocalDateTime.of(yearValue, monthValue, lastDayOfMonth,0,0);

        List<DesignerCalendarDto> designerCalendarDtos = new ArrayList<>();
        List<DesignerCalendarEntity> calendarList = designerCalendarRepo.findByStartBetween(start, end, Sort.by(Sort.Direction.ASC,"start"));
        for(DesignerCalendarEntity designerCalendarEntity :calendarList){
            DesignerCalendarDto dto = DesignerCalendarDto.of(designerCalendarEntity);
            designerCalendarDtos.add(dto);
        }
        return designerCalendarDtos;
    }


    public DesignerCalendarEntity calendarSave(DesignerCalendarDto calendarDto){
        DesignerCalendarEntity calendar = new DesignerCalendarEntity();
        calendar.setTitle(calendarDto.getTitle());
        calendar.setStart(convertToLocalDateTime(calendarDto.getStart()) );
        calendar.setEnd(convertToLocalDateTime(calendarDto.getEnd()));
        calendar.setAllDay(calendarDto.isAllDay());
        calendar.setEventType(calendarDto.getEventType()); // eventType 설정
        calendar.setName(calendarDto.getName());
        calendar.setWhoAmI("디자이너");
        return designerCalendarRepo.save(calendar);
    }

    public DesignerCalendarEntity calendarUpdate(DesignerCalendarDto calendarDto) {
        Long eventId = calendarDto.getId();

        Optional<DesignerCalendarEntity> optionalEvent = designerCalendarRepo.findById(eventId);

        if (!optionalEvent.isPresent()) {
            throw new EntityNotFoundException("해당 ID에 해당하는 이벤트를 찾을 수 없습니다: " + eventId);
        }

        DesignerCalendarEntity event = optionalEvent.get();
        event.setTitle(calendarDto.getTitle());
        event.setStart(event.getStart() );
        event.setEnd(event.getEnd());
        event.setAllDay(event.isAllDay());
        event.setEventType(event.getEventType()); // eventType 설정
        // 업데이트된 이벤트를 저장하고 반환합니다.
        return designerCalendarRepo.save(event);
    }

    public void calendarDelete(DesignerCalendarDto calendarDto) {

        Optional<DesignerCalendarEntity> optionalEvent = designerCalendarRepo.findById( calendarDto.getId() );

        if (!optionalEvent.isPresent()) {
            throw new EntityNotFoundException("해당 ID에 해당하는 이벤트를 찾을 수 없습니다: " + calendarDto.getId());
        }

        designerCalendarRepo.deleteById( calendarDto.getId() );
    }

    private static LocalDateTime convertToLocalDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(dateTimeString, formatter).plusHours(9);
    }
}