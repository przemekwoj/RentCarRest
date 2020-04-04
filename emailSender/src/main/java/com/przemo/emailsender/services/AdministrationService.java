package com.przemo.emailsender.services;

public interface AdministrationService {

    int numberOfAdministrationRecords();

    <U> U getPartOfAdministrationsEmails(int index, int batch_size);
}
