package com.przemo.emailsender.services;

import com.przemo.emailsender.repositories.queries.ClientQueries;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public <U> U getPartOfClientsEmails(int index, int batchSize) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<String> emails = session.createSQLQuery(ClientQueries.allClientsEmails).setFirstResult(index * batchSize).setMaxResults(batchSize).getResultList();
        transaction.commit();
        session.close();
        return (U) emails;
    }

    @Override
    public int numberOfClientRecords() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int numberOfRows = ((BigInteger) session.createSQLQuery(ClientQueries.numberOfClientRecords).getSingleResult()).intValue();
        transaction.commit();
        session.close();
        return numberOfRows;
    }

    @Override
    public int numberOfEmailRowsDetainedOrder() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int numberOfRows = ((BigInteger) session.createSQLQuery(ClientQueries.numberOfClientDetainedRows).getSingleResult()).intValue();
        transaction.commit();
        session.close();
        return numberOfRows;
    }

    @Override
    public <U> U getPartOfClientsWithDetainedOrder(int index, int batchSize) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<String> emails = session.createSQLQuery(ClientQueries.allClientsWithDetainedOrder).setFirstResult(index * batchSize).setMaxResults(batchSize).getResultList();
        transaction.commit();
        session.close();
        return (U) emails;
    }
}
