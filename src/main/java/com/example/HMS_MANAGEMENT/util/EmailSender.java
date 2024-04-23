package com.example.HMS_MANAGEMENT.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String recipientEmail, String subject, String content, String senderUsername, String senderPassword) {
        // SMTP 서버 설정
        String host = "smtp.naver.com";
        String port = "465";

        // 메일 속성 설정
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderUsername, senderPassword);
            }
        });

        try {
            // 메시지 객체 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(content);

            // 메일 전송
            Transport.send(message);
            System.out.println("이메일 전송 성공!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("이메일 전송 실패!");
        }
    }
}


