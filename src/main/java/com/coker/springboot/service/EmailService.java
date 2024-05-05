package com.coker.springboot.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    public void testEmail()  {
        String to = "nhattl.work@gmail.com";
        String subject = "Thông báo kết quả học tập";
        String body = "Chào Trần Long Nhật,\n" +
                "\n" +
                "Cảm ơn bạn đã dành thời gian tham gia ứng tuyển cho vị trí Java Developer tại CÔNG TY CỔ PHẦN BlueCo.\n" +
                "\n" +
                "Sau khi xem xét các hồ sơ ứng tuyển nhận được cho vị trí này, nhà tuyển dụng đã đánh giá CV của bạn là: Phù hợp";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
            helper.setFrom("longnhat41052002@gmail.com");

            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
