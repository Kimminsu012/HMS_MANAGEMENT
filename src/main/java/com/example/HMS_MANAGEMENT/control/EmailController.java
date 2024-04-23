package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.EmailDto;
import com.example.HMS_MANAGEMENT.service.EmailService;
import com.example.HMS_MANAGEMENT.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-salary-email")
    public ResponseEntity<String> sendSalaryEmail(@RequestBody EmailDto request) {
        try {
            emailService.sendSalaryEmail(request);
            return ResponseEntity.ok("이메일이 성공적으로 전송되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 전송 중 오류가 발생했습니다.");
        }
    }
}