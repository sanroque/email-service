package com.sanroque.email_service.controllers;

import com.sanroque.email_service.application.EmailSenderService;
import com.sanroque.email_service.core.EmailRequest;
import com.sanroque.email_service.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService service ){
        this.emailSenderService = service;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
        try {
            this.emailSenderService.sendEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("Email sent successfully");
        }
        catch(EmailServiceException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email" +" \n - " +  ex);
        }
    }
}
