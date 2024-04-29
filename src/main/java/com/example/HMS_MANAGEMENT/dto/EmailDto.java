
package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {
    private Long id;
    private String designerName;
    private String designerEmail;
    private String basicSal;
    private String overtimeAllowance;
    private String mealAllowance;
    private String pension;
    private String healthInsurance;
    private String employmentInsurance;
    private String incomeTax;
    private String netSalary;
    private String emailContent;
}
