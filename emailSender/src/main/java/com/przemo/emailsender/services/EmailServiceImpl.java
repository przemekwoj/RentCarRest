package com.przemo.emailsender.services;

import com.przemo.emailsender.consumerWrapper.ConsumerWrapper;
import com.przemo.emailsender.models.Attachment;
import com.przemo.emailsender.models.Email;
import com.przemo.emailsender.reveivers.QueueType;
import com.przemo.emailsender.reveivers.ReceiversService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private final ReceiversService receiversService;

    public EmailServiceImpl(JavaMailSender emailSender, ReceiversService receiversService) {
        this.emailSender = emailSender;
        this.receiversService = receiversService;
    }

    @Override
    public void sendEmail(String to, String subject, String text, List<Attachment> attachments) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        addAttachments(helper, attachments);
        emailSender.send(message);
    }

    private void addAttachments(MimeMessageHelper helper, List<Attachment> attachments) throws MessagingException {
        for (Attachment attachment : attachments) {
            InputStreamSource inputStreamSource = new ByteArrayResource(attachment.getAttachment_decoded_b64());
            helper.addAttachment(attachment.getFileName(), inputStreamSource);
        }
    }

    @Override
    public void sendEmails(Email emailInfo) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        sendEmailsPartially(emailInfo, executorService);
    }

    private void sendEmailsPartially(Email emailInfo, ExecutorService executorService) {
        for (int index = 0; index < receiversService.amountOfBatchFetch(emailInfo.getReceivers()); index++) {
            CompletableFuture<List<String>> partOrEmails = getPartOfEmails(emailInfo.getReceivers(), index, executorService);
            partOrEmails.thenAcceptAsync(receivers -> sendEmailToReceivers(receivers, emailInfo));
        }

    }

    private void sendEmailToReceivers(List<String> receivers, Email emailInfo) {
        receivers.forEach(ConsumerWrapper.throwingConsumerWrapper(receiver -> sendEmail(receiver, emailInfo.getSubject(), emailInfo.getMessage(), emailInfo.getAttachments())));
    }


    private CompletableFuture<List<String>> getPartOfEmails(QueueType queueType, int index, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> receiversService.getPartOfReceivers(queueType, index), executorService);
    }
}
