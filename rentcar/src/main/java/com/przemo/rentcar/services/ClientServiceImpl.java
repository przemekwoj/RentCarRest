package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.users.Client;
import com.przemo.rentcar.repositoriesDB.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client updateClient(Client client, Long clientId) {
        client.setUser_id(clientId);
        return clientRepository.save(client);
    }

    @Override
    public Client getOneClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
