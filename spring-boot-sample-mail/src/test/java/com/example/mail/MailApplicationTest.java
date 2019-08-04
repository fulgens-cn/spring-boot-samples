package com.example.mail;

import com.example.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTest {

    @Value("${application.mail.to}")
    private String to;

    @Autowired
    private MailService mailService;

    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail(to, "测试文本邮箱发送", "邮件内容");
    }

    @Test
    public void sendHtmlMailTest() {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(to,"测试Html邮件发送", content);
    }

    @Test
    public void sendAttachmentsMailTest() {
        String filePath = "/Users/fulgens/Documents/servlet-3_1-final.pdf";
        mailService.sendAttachmentsMail(to, "测试带附件的邮件发送", "文档见附件请查收！", filePath);
    }

    @Test
    public void sendInlineResourceTest() {
        String rscId = "oxlo85";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "/Users/fulgens/Pictures/wallhaven-oxlo85.jpg";

        mailService.sendInlineResourceMail(to, "测试图片邮件发送", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMailTest() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("url", "https://spring.io/");

        mailService.sendTemplateMail(to, "测试模板邮件", "mailTemplate", variables);
    }
}