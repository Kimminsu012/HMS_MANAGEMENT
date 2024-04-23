package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.EmailDto;
import com.example.HMS_MANAGEMENT.util.EmailSender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Value("${email.sender.username}")
    private String senderUsername;

    @Value("${email.sender.password}")
    private String senderPassword;

    public void sendSalaryEmail(EmailDto request) {
        // 이메일 본문 생성
        String emailContent = "디자이너: " + request.getDesignerName() + "\n" +
                "기본급: " + request.getBasicSal() + "\n" +
                "초과근무수당: " + request.getOvertimeAllowance() + "\n" +
                "식비: " + request.getMealAllowance() + "\n" +
                "국민연금: " + request.getPension() + "\n" +
                "건강보험: " + request.getHealthInsurance() + "\n" +
                "고용보험: " + request.getEmploymentInsurance() + "\n" +
                "근로소득세: " + request.getIncomeTax() + "\n" +
                "실 수령액: " + request.getNetSalary();

        // 이메일 보내기
        EmailSender.sendEmail(request.getDesignerEmail(), "급여명세서", emailContent, senderUsername, senderPassword);
    }
}