package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.InvenDto;
import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import com.example.HMS_MANAGEMENT.repository.InvenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InvenService {

    private final InvenRepo invenRepo;


    public InvenEntity invenSave(InvenDto dto){

        InvenEntity inven = new InvenEntity();
        inven.setId(dto.getId());
        inven.setCount(dto.getCount());
        inven.setItemL(dto.getItemL());
        inven.setIdClass(dto.getIdClass());
        inven.setItemNm(dto.getItemNm());
        inven = invenRepo.save(inven);

        return inven;

    }

    public void processBuy(InvenDto invenDto) {
        invenSave(invenDto);
    }

    public void processSell(InvenDto invenDto) {
        invenRepo.deleteById(invenDto.getId());
    }

    public void processModify(InvenDto invenDto) {
        InvenEntity inven = invenRepo.findById(invenDto.getId()).orElse(null);
        if (inven != null) {
            inven.setItemNm(invenDto.getItemNm());
            inven.setCount(invenDto.getCount());
            inven.setItemL(invenDto.getItemL());
            inven.setIdClass(invenDto.getIdClass());
            invenRepo.save(inven);
        }
    }
}
