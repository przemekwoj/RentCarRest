package com.przemo.emailsender.services;


public interface ClientService {
    int numberOfEmailRowsDetainedOrder();

    <U> U getPartOfClientsWithDetainedOrder(int index, int batchSize);

    int numberOfClientRecords();

    <U> U getPartOfClientsEmails(int index, int batch_size);
}
