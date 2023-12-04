package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.events.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationService {

    private final EmailService emailService;

    public UserActivationService(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener(UserRegisteredEvent.class)
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        // TODO: Add activation links
        System.out.println("User with email " + userRegisteredEvent.getUserEmail());
        emailService.sendRegistrationEmail(userRegisteredEvent.getUserEmail(), userRegisteredEvent.getUsernames());
    }
}
