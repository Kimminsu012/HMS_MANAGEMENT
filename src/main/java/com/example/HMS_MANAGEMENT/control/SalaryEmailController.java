package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.EmailDto;
import com.example.HMS_MANAGEMENT.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/send-salary-email")
public class SalaryEmailController {

    private final EmailService emailService;

    public SalaryEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public String sendSalaryEmail(@RequestBody EmailDto emailDto, HttpServletRequest request) {
        try {
            emailService.sendSalaryEmail(emailDto, request);
            return "success"; // 이메일이 성공적으로 보내졌을 경우
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 이메일 보내기에 실패했을 경우
        }


    }
}