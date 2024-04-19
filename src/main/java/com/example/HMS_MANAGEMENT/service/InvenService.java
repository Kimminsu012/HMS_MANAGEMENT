package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import com.example.HMS_MANAGEMENT.dto.InvenDto;
import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import com.example.HMS_MANAGEMENT.repository.InvenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        // 판매할 제품을 데이터베이스에서 조회합니다.
        Optional<InvenEntity> optionalInven = invenRepo.findById(id);
        if (optionalInven.isPresent()) {
            InvenEntity inven = optionalInven.get();
            int currentQuantity = inven.getCount();
            // 판매할 수량이 현재 재고보다 작거나 같은지 확인합니다.
            if (currentQuantity >= quantity && quantity > 0) {
                // 새로운 엔티티를 생성하여 판매 정보를 저장합니다.
                InvenEntity saleRecord = new InvenEntity();
                saleRecord.setIdCode(inven.getIdCode());
                saleRecord.setItemNm(inven.getItemNm());
                saleRecord.setCount(quantity * -1); // 판매는 수량을 음수로 저장합니다.
                saleRecord.setItemL(inven.getItemL());
                saleRecord.setIdClass(inven.getIdClass());
                saleRecord.setInvenStatus(InvenStatus.SELL); // 판매 상태로 설정합니다.
                invenRepo.save(saleRecord);
                // 현재 재고 정보를 갱신합니다.
                inven.setCount(currentQuantity - quantity);
                invenRepo.save(inven);
            } else if (quantity <= 0) {
                // 판매할 수량이 0보다 작거나 같은 경우에 대한 처리
                throw new IllegalArgumentException("판매 수량은 0보다 커야 합니다.");
            } else {
                // 판매할 수량이 재고보다 많은 경우에 대한 처리
                throw new RuntimeException("재고가 부족합니다.");
            }
        } else {
            // 해당 아이템이 재고에 없는 경우에 대한 처리
            throw new RuntimeException("해당 제품이 존재하지 않습니다.");
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
