package com.przemo.rentcar.services;

import com.przemo.rentcar.users.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService
{
    Client addClient(Client client);

    void deleteClient(Long clientId);

    Client updateClient(Client client,Long clientId);

    Optional<Client> getOneClient(Long id);

    List<Client> getAllClients();
}
