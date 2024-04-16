package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetailDto {

    private Long id;
    private String detailDate;
    private String detailDesigner;
    private String detailType;
    private String detailCost;
}