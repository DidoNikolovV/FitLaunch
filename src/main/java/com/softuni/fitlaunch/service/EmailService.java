package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.entity.UserEntity;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

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

    public void sendReminderEmail(UserEntity user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);


        try {
            mimeMessageHelper.setFrom(fitlaunch);
            mimeMessageHelper.setFrom(fitlaunch);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject("Daily workout reminder!");
            mimeMessageHelper.setText("Have you trained today?");

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch(MessagingException e) {
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
