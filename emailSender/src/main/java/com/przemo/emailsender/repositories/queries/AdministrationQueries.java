package com.przemo.emailsender.repositories.queries;

public interface AdministrationQueries {

    String allAdministrationEmails = "SELECT administration.email as email\n" +
            "FROM administration\n";

    String numberOfAdministrationRecords = "SELECT COUNT(*)\n" +
            "FROM administration\n";
}
