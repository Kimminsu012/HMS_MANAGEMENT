package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Service
public class EmailService {

    public void sendSalaryEmail(EmailDto emailDto, HttpServletRequest request) throws MessagingException {
        String to = emailDto.getDesignerEmail();
        String from = "km7081@naver.com"; // 보내는 사람의 이메일 주소
        String password = "CBRF3JVRH47Q"; // 보내는 사람의 이메일 계정 비밀번호
        String host = "smtp.naver.com"; // 네이버 메일 서버 호스트 이름

        // SMTP 프로토콜 설정
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");

        // 보내는 사람 계정 정보 설정
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // 메일 내용 작성
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("급여명세서");

        // 메일 내용 설정
        StringBuilder content = new StringBuilder();
        content.append("디자이너: ").append(emailDto.getDesignerName()).append("\n")
                .append("기본급: ").append(emailDto.getBasicSal()).append("\n")
                .append("초과근무수당: ").append(emailDto.getOvertimeAllowance()).append("\n")
                .append("식비: ").append(emailDto.getMealAllowance()).append("\n")
                .append("국민연금: ").append(emailDto.getPension()).append("\n")
                .append("건강보험: ").append(emailDto.getHealthInsurance()).append("\n")
                .append("고용보험: ").append(emailDto.getEmploymentInsurance()).append("\n")
                .append("근로소득세: ").append(emailDto.getIncomeTax()).append("\n")
                .append("실 수령액: ").append(emailDto.getNetSalary());

        message.setText(content.toString());

        // 메일 보내기
        Transport.send(message);
    }
}