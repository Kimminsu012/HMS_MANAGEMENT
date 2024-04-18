package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.InvenDto;
import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import com.example.HMS_MANAGEMENT.repository.InvenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvenService {

    private final InvenRepo invenRepo;


    public InvenEntity invenSave(InvenDto dto){

        InvenEntity inven = new InvenEntity();
        inven.setId(dto.getId());
        inven.setIdCode(dto.getIdCode());
        inven.setCount(dto.getCount());
        inven.setItemL(dto.getItemL());
        inven.setIdClass(dto.getIdClass());
        inven.setItemNm(dto.getItemNm());
        inven.setInvenStatus(dto.getInvenStatus());
        inven = invenRepo.save(inven);

        return inven;

    }

    public void processBuy(InvenDto invenDto) {
        InvenEntity inven = invenRepo.findByItemNmAndIdCodeAndInvenStatus(
                invenDto.getItemNm(), invenDto.getIdCode(), invenDto.getInvenStatus()
        );
        if( inven != null ){
            inven.setCount(inven.getCount()+invenDto.getCount());
            invenRepo.save(inven);
        }else{
            invenSave(invenDto);
        }


    }

    public void processSell(Long id, int quantity) {
        InvenEntity inven = invenRepo.findById(id).orElse(null);
        if (inven != null) {
            int currentQuantity = inven.getCount();
            if (currentQuantity >= quantity) {
                inven.setCount(currentQuantity - quantity);
                invenRepo.save(inven);
            } else {
                // 판매할 수량이 현재 재고보다 많습니다. 처리할 수 있는 로직 추가
            }
        } else {
            // 해당 아이템이 재고에 없습니다. 처리할 수 있는 로직 추가
        }
    }

    public void processModify(InvenDto invenDto) {
        InvenEntity inven = invenRepo.findById(invenDto.getId()).orElse(null);
        if (inven != null) {
            inven.setItemNm(invenDto.getItemNm());
            inven.setIdCode(invenDto.getIdCode());
            inven.setCount(invenDto.getCount());
            inven.setItemL(invenDto.getItemL());
            inven.setIdClass(invenDto.getIdClass());
            inven.setInvenStatus(invenDto.getInvenStatus());
            invenRepo.save(inven);
        }
    }

    public List<InvenDto> getAllInventoryItems() {
        // 모든 재고 항목을 데이터베이스에서 가져옵니다.
        List<InvenEntity> invenEntities = invenRepo.findAll();
        // InvenEntity를 InvenDto로 변환하여 반환합니다.
        return invenEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private InvenDto convertToDto(InvenEntity entity) {
        InvenDto dto = new InvenDto();
        dto.setId(entity.getId());
        dto.setItemNm(entity.getItemNm());
        dto.setInvenStatus(entity.getInvenStatus());
        dto.setIdCode(entity.getIdCode());
        dto.setIdClass(entity.getIdClass());
        dto.setItemL(entity.getItemL());
        dto.setCount(entity.getCount());
        return dto;
    }
}
