package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.EmailDto;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public void sendSalaryEmail(EmailDto emailDto) throws MessagingException {
        String to = emailDto.getDesignerEmail();
        String from = "your-email@example.com"; // 보내는 사람 이메일 주소
        String password = "your-email-password"; // 보내는 사람 이메일 계정 비밀번호
        String host = "smtp.example.com"; // 이메일 호스트 (예: smtp.gmail.com)

        // SMTP 프로토콜 설정
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "465"); // SMTP 포트 번호
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true"); // SSL 사용 여부

        // 보내는 사람 계정 정보 설정
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // 메일 내용 작성
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("급여명세서");
        msg.setText(emailDto.getEmailContent());

        // 메일 보내기
        Transport.send(msg);
    }
}