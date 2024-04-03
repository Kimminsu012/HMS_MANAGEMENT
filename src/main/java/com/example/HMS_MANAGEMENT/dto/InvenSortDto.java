package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InvenSortDto {
    private Long id; // 제품 코드

    private Integer itemNm; // 제품 이름

    private Integer count; // 제품 수량

    private String idCalss; // 제품 분류

    private Integer itemL; // 제품 용량
}
