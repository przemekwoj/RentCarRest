package com.przemo.rentcar.services;

import com.przemo.rentcar.repositoriesDB.ClientRepository;
import com.przemo.rentcar.users.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client updateClient(Client client,Long clientId) {
        client.setUser_id(clientId);
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> getOneClient(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
