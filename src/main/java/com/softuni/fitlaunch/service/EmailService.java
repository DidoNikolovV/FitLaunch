package com.softuni.fitlaunch.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final String fitlaunch;

    public EmailService(
            TemplateEngine templateEngine,
            JavaMailSender javaMailSender,
            @Value("${spring.mail.fitlaunch}") String fitlaunch) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.fitlaunch = fitlaunch;
    }

    public void sendRegistrationEmail(String userEmail, String username, String activationCode) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();


        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setFrom(fitlaunch);
            mimeMessageHelper.setReplyTo(fitlaunch);
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject("Welcome to FitLaunch!");
            mimeMessageHelper.setText(generateRegistrationEmailBody(username, activationCode), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateRegistrationEmailBody(String username, String activation_code) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("activation_code", activation_code);
        return templateEngine.process("email/registration-email", context);
    }
}
