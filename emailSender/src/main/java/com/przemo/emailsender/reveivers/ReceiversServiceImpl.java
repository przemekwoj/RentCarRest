package com.przemo.emailsender.reveivers;

import com.przemo.emailsender.services.AdministrationService;
import com.przemo.emailsender.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiversServiceImpl implements ReceiversService {

    private final int BATCH_SIZE = 20;

    private final AdministrationService administrationService;
    private final ClientService clientService;

    public ReceiversServiceImpl(AdministrationService administrationService, ClientService clientService) {
        this.administrationService = administrationService;
        this.clientService = clientService;
    }

    @Override
    public int amountOfBatchFetch(QueueType queueType) {
        int numberOfRecords = 0;
        switch (queueType) {
            case NOTIFY_ALL:
                numberOfRecords = clientService.numberOfClientRecords();
                break;
            case DETAINED_ORDER:
                numberOfRecords = clientService.numberOfEmailRowsDetainedOrder();
                break;
            case NOTIFY_ADMINISTRATION:
                numberOfRecords = administrationService.numberOfAdministrationRecords();
        }
        return numberOfRecords / BATCH_SIZE;
    }

    @Override
    public List<String> getPartOfReceivers(QueueType queueType, int index) {
        List<String> partOfEmails = new ArrayList<>();
        switch (queueType) {
            case NOTIFY_ALL:
                partOfEmails = clientService.getPartOfClientsEmails(index, BATCH_SIZE);
                break;
            case DETAINED_ORDER:
                partOfEmails = clientService.getPartOfClientsWithDetainedOrder(index, BATCH_SIZE);
                break;
            case NOTIFY_ADMINISTRATION:
                partOfEmails = administrationService.getPartOfAdministrationsEmails(index, BATCH_SIZE);
                break;
        }
        return partOfEmails;
    }


}
