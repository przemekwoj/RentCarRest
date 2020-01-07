package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.users.Client;

import java.util.List;

public interface ClientService
{
    Client addClient(Client client);

    void deleteClient(Long clientId);

    Client updateClient(Client client,Long clientId);

    Client getOneClient(Long id);

    List<Client> getAllClients();
}
