package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.DesignerDto;
import com.example.HMS_MANAGEMENT.entity.DayOffEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import com.example.HMS_MANAGEMENT.repository.DayOffRepo;
import com.example.HMS_MANAGEMENT.repository.DesignerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DesignerService {

    private final DayOffRepo dayOffRepo;
    private final DesignerRepo designerRepo;

    public DesignerEntity designerSave(DesignerDto dto) {
        DesignerEntity designer = new DesignerEntity();
        designer.setName(dto.getName());
        designer.setTel(dto.getTel());
        designer.setDate(dto.getDate());
        designer.setSal(dto.getSal());
        designer = designerRepo.save(designer);
        return designer;
    }

    public List<DesignerDto> getAllDesigners() {
        List<DesignerEntity> designers = designerRepo.findAll();
        return designers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<DesignerDto> getAllDesignersWithDayOff() {
        List<DesignerEntity> designers = designerRepo.findAll();
        List<DesignerDto> designerDtos = new ArrayList<>();
        for (DesignerEntity designerEntity : designers) {
            DesignerDto dto = convertToDto(designerEntity);
            List<String> dayOffs = getDayOffsByDesignerId(designerEntity.getId());
            dto.setDayOffList(dayOffs);
            designerDtos.add(dto);
        }
        return designerDtos;
    }

    public List<String> getDayOffsByDesignerId(Long designerId) {
        List<DayOffEntity> dayOffEntities = dayOffRepo.findByDesignerId(designerId);
        return dayOffEntities.stream()
                .map(DayOffEntity::getDay)
                .collect(Collectors.toList());
    }

    private DesignerDto convertToDto(DesignerEntity designerEntity) {
        DesignerDto dto = new DesignerDto();
        dto.setId(designerEntity.getId());
        dto.setName(designerEntity.getName());
        dto.setTel(designerEntity.getTel());
        dto.setDate(designerEntity.getDate());
        dto.setSal(designerEntity.getSal());
        return dto;
    }
}
