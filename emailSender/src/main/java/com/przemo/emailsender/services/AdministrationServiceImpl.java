package com.przemo.emailsender.services;

import com.przemo.emailsender.repositories.queries.AdministrationQueries;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;


@Service
public class AdministrationServiceImpl implements AdministrationService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public <U> U getPartOfAdministrationsEmails(int index, int batchSize) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<String> emails = session.createSQLQuery(AdministrationQueries.allAdministrationEmails).setFirstResult(index * batchSize).setMaxResults(batchSize).getResultList();
        transaction.commit();
        session.close();
        return (U) emails;
    }

    @Override
    public int numberOfAdministrationRecords() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int numberOfRows = ((BigInteger) session.createSQLQuery(AdministrationQueries.numberOfAdministrationRecords).getSingleResult()).intValue();
        transaction.commit();
        session.close();
        return numberOfRows;
    }

}
