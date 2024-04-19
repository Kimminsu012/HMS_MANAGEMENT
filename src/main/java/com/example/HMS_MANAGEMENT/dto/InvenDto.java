package com.example.HMS_MANAGEMENT.dto;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class InvenDto {

    private Long id; // 제품 번호

    @NotNull(message = "제품 코드를 입력 또는 선택 해주세요.")
    private Integer idCode; // 제문 코드

    @NotNull(message = "제품명을 입력 해주세요.")
    private String itemNm; // 제품 이름

    @NotNull(message = "수량은 1개 이상 , 재고수량 확인 해주세요.")
    private Integer count; // 제품 수량

    @NotNull(message = "제품 종류를 입력 해주세요.")
    private String idClass; // 제품 분류

    @NotNull(message = "제품 용량을 입력 해주세요.")
    private Integer itemL; // 제품 용량

    @NotNull(message = "등록하는 방식을 선택 해주세요.")
    private InvenStatus invenStatus; // 분류


}
