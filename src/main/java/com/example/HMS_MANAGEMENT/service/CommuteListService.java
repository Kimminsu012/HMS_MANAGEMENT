package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.CommuteListDto;
import com.example.HMS_MANAGEMENT.entity.CommuteListEntity;
import com.example.HMS_MANAGEMENT.repository.CommuteListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommuteListService {

    private final CommuteListRepo commuteListRepo;

    public CommuteListEntity commuteSave(CommuteListDto dto){
        CommuteListEntity commuteList = new CommuteListEntity();
        commuteList.setCommuteStatus(dto.getCommuteStatus());
        commuteList.setMorningTime(dto.getMorningTime());
        commuteList.setAfterTime(dto.getAfterTime());
        LocalTime resultTime = dto.getMorningTime().minusHours(dto.getAfterTime().getHour()).minusMinutes(dto.getAfterTime().getMinute());
        commuteList.setResultTime(resultTime);
        commuteList.setDate(LocalDate.now());
        commuteList.setTime(LocalTime.now());
        commuteList.setName(dto.getName());
        commuteList.setATime(dto.getATime());
        commuteList.setMTime(dto.getMTime());
        commuteList = commuteListRepo.save(commuteList);

        return commuteList;

    }

    public CommuteListDto convertToDto(CommuteListEntity entity){
        CommuteListDto dto = new CommuteListDto();
        dto.setId(entity.getId());
        dto.setCommuteStatus(entity.getCommuteStatus());
        dto.setMorningTime(entity.getMorningTime());
        dto.setAfterTime(entity.getAfterTime());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setATime(entity.getATime());
        dto.setMTime(entity.getMTime());
        dto.setName(entity.getName());

        LocalTime resultTime = entity.getMorningTime().minusHours(entity.getAfterTime().getHour()).minusMinutes(entity.getAfterTime().getMinute());
        dto.setResultTime(resultTime);

        return dto;
    }

    public List<CommuteListDto> getAllCommute(){
        List<CommuteListEntity> commuteListEntities = commuteListRepo.findAll();
        return commuteListEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
