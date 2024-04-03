package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
public class DesignerDto {

    private Long id; // 디자이너 번호

    private String name; // 디자이너 이름

    private Integer phonNum; // 연락처

    private Integer salary; // 월급

    private String free; // 휴일
}
