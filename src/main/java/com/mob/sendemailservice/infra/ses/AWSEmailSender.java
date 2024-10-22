package com.mob.sendemailservice.infra.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.mob.sendemailservice.adapters.EmailSenderGateway;
import com.mob.sendemailservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AWSEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService emailService;

    @Autowired
    public AWSEmailSender(AmazonSimpleEmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                                            .withSource("matheusoliveira0890@gmail.com")
                                            .withDestination(new Destination().withToAddresses(to))
                                            .withMessage(new Message()
                                                        .withSubject(new Content(subject))
                                                        .withBody(new Body().withText(new Content(body))));
        try {
            this.emailService.sendEmail(sendEmailRequest);
        }catch (AmazonSimpleEmailServiceException e){
            throw new EmailServiceException("Canceling email sending", e);
        }
    }
}
