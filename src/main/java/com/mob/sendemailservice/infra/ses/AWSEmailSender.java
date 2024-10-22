package com.mob.sendemailservice.infra.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.mob.sendemailservice.adapters.EmailSenderGateway;
import com.mob.sendemailservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // define classe como um serviço onde terá toda a lógica do projeto
public class AWSEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService emailService;

    @Autowired // o autowired certifica de utilizar aquele bean criado em AwsSesConfig para injetar a dependência automaticamente
    public AWSEmailSender(AmazonSimpleEmailService emailService) {
        this.emailService = emailService;
    }

    @Override // método que vem da inteface Gateway
    public void sendEmail(String to, String subject, String body) {
        // objeto para definir enviador e informações do email
        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                                            .withSource("matheusoliveira0890@gmail.com")  // substitua pelo seu email que voce verificou no serviço Amazon ses
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
