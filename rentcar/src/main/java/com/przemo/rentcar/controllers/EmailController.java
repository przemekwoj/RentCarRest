package com.przemo.rentcar.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.przemo.rentcar.models.Email;
import com.przemo.rentcar.services.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
@Transactional
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendEmail")
    public Email sendEmail(@RequestBody Email email) throws JsonProcessingException {
        emailService.pushEmailToRabbitQueue(email);
        return email;
    }
}
