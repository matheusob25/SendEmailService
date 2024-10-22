package com.mob.sendemailservice.core;

public interface EmailSenderUseCase {
    void sendEmail(String to, String subject, String body);
}
