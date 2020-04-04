package com.przemo.emailsender.emailSenders;

import com.przemo.emailsender.consumerWrapper.ConsumerWrapper;
import com.przemo.emailsender.models.Email;
import com.przemo.emailsender.repositories.queries.ClientQueries;
import com.przemo.emailsender.reveivers.QueueType;
import com.przemo.emailsender.reveivers.ReceiversService;
import com.przemo.emailsender.reveivers.ReceiversServiceImpl;
import com.przemo.emailsender.services.ClientService;
import com.przemo.emailsender.services.EmailService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ScheduledEmails implements Job {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReceiversService receiversService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        sendDetainedEmailInformation();
    }

    private void sendDetainedEmailInformation() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        sendEmailsPartially(executorService);
    }

    private void sendEmailsPartially(ExecutorService executorService) {
        for (int index = 0; index < receiversService.amountOfBatchFetch(QueueType.DETAINED_ORDER); index++) {
            CompletableFuture<List<String>> partOrEmails = getPartOfEmails(QueueType.DETAINED_ORDER, index, executorService);
            partOrEmails.thenAcceptAsync(receivers -> sendEmailToReceivers(receivers));
        }
    }

    private void sendEmailToReceivers(List<String> receivers) {
        receivers.forEach(ConsumerWrapper.throwingConsumerWrapper(receiver ->
                emailService.sendEmail(receiver, "Detained", "Your Order is detained", Collections.emptyList()))
        );

    }

    private CompletableFuture<List<String>> getPartOfEmails(QueueType detainedOrder, int index, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> receiversService.getPartOfReceivers(detainedOrder, index), executorService);
    }
}

