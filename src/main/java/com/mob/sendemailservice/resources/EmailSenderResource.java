package com.mob.sendemailservice.resources;


import com.mob.sendemailservice.application.EmailSenderService;
import com.mob.sendemailservice.core.EmailRequest;
import com.mob.sendemailservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderResource {
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderResource(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
        try {
          this.emailSenderService.sendEmail(request.to(), request.subject(), request.body());
          return ResponseEntity.ok("Email sent successfully");
        }catch (EmailServiceException e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email: " + e.getMessage());
        }
    }
}
