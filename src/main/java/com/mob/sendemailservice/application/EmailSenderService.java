package com.mob.sendemailservice.application;

import com.mob.sendemailservice.adapters.EmailSenderGateway;
import com.mob.sendemailservice.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGateway gateway;

    @Autowired
    public EmailSenderService(EmailSenderGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.gateway.sendEmail(to, subject, body);
    }
}
