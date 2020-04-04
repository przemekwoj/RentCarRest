package com.przemo.emailsender.services;

import com.przemo.emailsender.models.Attachment;
import com.przemo.emailsender.models.Email;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface EmailService {
    void sendEmail(String to, String subject, String text, List<Attachment> attachments) throws MessagingException, IOException;
    void sendEmails(Email emailInfo);
}
