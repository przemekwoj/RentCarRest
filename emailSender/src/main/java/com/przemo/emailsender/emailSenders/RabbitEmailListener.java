package com.przemo.emailsender.emailSenders;

import com.przemo.emailsender.consumerWrapper.ConsumerWrapper;
import com.przemo.emailsender.converter.RabbitConverterImpl;
import com.przemo.emailsender.models.Email;
import com.przemo.emailsender.reveivers.QueueType;
import com.przemo.emailsender.reveivers.ReceiversService;
import com.przemo.emailsender.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


import java.io.IOException;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class RabbitEmailListener {

    private final RabbitConverterImpl rabbitConverter;

    private final EmailService emailService;


    public RabbitEmailListener(RabbitConverterImpl rabbitConverter, EmailService emailService) {
        this.rabbitConverter = rabbitConverter;
        this.emailService = emailService;
    }


    @RabbitListener(queues = "notifyAll")
    public void notifyAll(Message message) throws IOException {
        Email emailInfo = rabbitConverter.convertRabbitMessageToEmailInfo(message);
        emailService.sendEmails(emailInfo);
    }

    @RabbitListener(queues = "detainedOrder")
    public void directMail(Message message) throws IOException {
        Email emailInfo = rabbitConverter.convertRabbitMessageToEmailInfo(message);
        emailService.sendEmails(emailInfo);
    }

    @RabbitListener(queues = "notifyAdministration")
    public void notifyAdministration(Message message) throws IOException {
        Email emailInfo = rabbitConverter.convertRabbitMessageToEmailInfo(message);
        emailService.sendEmails(emailInfo);
    }
}
