package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
public class DesignerDto {


    private Long id; // 디자이너 번호

    @NotNull(message = "이름을 입력 해주세요.")
    private String name; // 디자이너 이름
    @NotNull(message = "전화번호를 입력 해주세요.")
    private Integer phonNum; // 연락처
    @NotNull(message = "등록일을 선택 해주세요.")
    private Date date;
    @NotNull(message = "월급을 입력 해주세요.")
    private Integer salary; // 월급
    @NotNull(message = "휴일을 선택 해주세요.")
    private String free; // 휴일
}
