package com.example.HMS_MANAGEMENT.dto;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
public class InvenDto {

    private Long id; // 제품 코드

    private String itemNm; // 제품 이름

    private Integer count; // 제품 수량

    private String idClass; // 제품 분류

    private Integer itemL; // 제품 용량

    private InvenStatus invenStatus; // 분류

    public String invenStatus() {
        return this.invenStatus();
    }
}
