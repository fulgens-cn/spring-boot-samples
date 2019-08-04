package com.example.mail.service.impl;

import com.example.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${application.mail.from}")
    private String from;

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public MailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setSentDate(new Date());

        mailSender.send(mailMessage);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(mimeMessage);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送html邮件时发生异常！", e);
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            mailSender.send(mimeMessage);
            log.info("带附件邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件异常！", e);
        }

    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource resource = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, resource);

            mailSender.send(message);
            log.info("嵌入静态资源的邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }

    @Override
    public void sendTemplateMail(String to, String subject, String template, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);

        String content = templateEngine.process(template, context);
        sendHtmlMail(to, subject, content);
    }
}
