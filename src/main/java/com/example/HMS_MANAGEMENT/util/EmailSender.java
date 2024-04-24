package com.example.HMS_MANAGEMENT.util;

import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import com.example.HMS_MANAGEMENT.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(HttpServletRequest request) throws Exception {

        String to = "km7081@naver.com"; // 받는 사람의 이메일 주소
        String from = "km7081@naver.com"; // 보내는 사람의 이메일 주소
        String password = "HZ63LFDFYZ9P!"; // 보내는 사람의 이메일 계정 비밀번호
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
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("급여명세서");
        msg.setText("디자이너: " + request.getParameter("designerName") + "\n" +
                "기본급: " + request.getParameter("basicSal") + "\n" +
                "초과근무수당: " + request.getParameter("overtimeAllowance") + "\n" +
                "식비: " + request.getParameter("mealAllowance") + "\n" +
                "국민연금: " + request.getParameter("pension") + "\n" +
                "건강보험: " + request.getParameter("healthInsurance") + "\n" +
                "고용보험: " + request.getParameter("employmentInsurance") + "\n" +
                "근로소득세: " + request.getParameter("incomeTax") + "\n" +
                "실 수령액: " + request.getParameter("netSalary"));

        // 메일 보내기
        Transport.send(msg);
    }
}