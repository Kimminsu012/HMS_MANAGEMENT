package com.example.HMS_MANAGEMENT.control;

import com.azure.core.annotation.Get;
import com.example.HMS_MANAGEMENT.dto.DesignerCalendarDto;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import com.example.HMS_MANAGEMENT.service.DesignerCalendarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/designer")
public class DesignerCalendarController {
    private final DesignerCalendarService calendarService;

    @Autowired
    public DesignerCalendarController(DesignerCalendarService calendarService){
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar")
    public ResponseEntity<String> getCalendar() throws JsonProcessingException {
        List<DesignerCalendarDto> dtoList= calendarService.getCalendarData();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dtoList);

        return ResponseEntity.ok(json);
    }
    @PostMapping("/calendarSave")
    public ResponseEntity<DesignerCalendarEntity> calendarSave(@RequestBody DesignerCalendarDto calendarDto){
        DesignerCalendarEntity savedEvent = calendarService.calendarSave(calendarDto);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @PostMapping("/calendarUpdate")
    public ResponseEntity<DesignerCalendarEntity> calendarUpdate(@RequestBody DesignerCalendarDto calendarDto){
        DesignerCalendarEntity updatedEvent = calendarService.calendarUpdate(calendarDto);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @PostMapping("/calendarDelete")
    public ResponseEntity<?> calendarDelete(@RequestBody DesignerCalendarDto calendarDto){
        calendarService.calendarDelete(calendarDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}