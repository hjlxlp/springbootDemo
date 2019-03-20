package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hjl
 * @date 2019/2/11 14:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTest {

    @Autowired
    JavaMailSender mailSender;

    @Test
    public void sendSimpleEmail() {
        try {
            String deliver = "xx@qq.com";
            String[] receiver = {"xx@qq.com"};
            String[] carbonCopy = {};
            String subject = "";
            String content = "";
            SimpleMailMessage message = new SimpleMailMessage();
            for (int i = 1; i < 11; i++) {
                subject = "第" + i + "封";
                content = "第" + i + "遍";
                message.setFrom(deliver);
                message.setTo(receiver);
                message.setCc(carbonCopy);
                message.setSubject(subject);
                message.setText(content);
                mailSender.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
