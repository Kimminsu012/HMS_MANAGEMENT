package com.example.HMS_MANAGEMENT.controller;

import com.example.HMS_MANAGEMENT.util.EmailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/send-salary-email")
public class SalaryEmailController {

    @PostMapping
    public String sendSalaryEmail(HttpServletRequest request) {
        try {
            EmailSender.sendEmail(request);
            return "success"; // 이메일이 성공적으로 보내졌을 경우
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 이메일 보내기에 실패했을 경우
        }
    }
}