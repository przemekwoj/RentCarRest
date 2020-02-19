package com.przemo.rentcar.controllers;

import com.przemo.rentcar.services.ClientService;
import com.przemo.rentcar.entities.users.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@Transactional
@Validated
@RequestMapping("/client")
public class ClientController
{
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}")
    public Client getClientById(@PathVariable Long clientId)
    {
        return clientService.getOneClient(clientId);
    }

    @GetMapping("/clients")
    public  List<Client> getClients()
    {
        return clientService.getAllClients();
    }

    @PostMapping("/client")
    public  Client addClient(@RequestBody @Valid Client client)
    {
        return clientService.addClient(client);
    }

    @PostMapping("/{clientId}")
    public  Client addClient(@RequestBody Client client,@RequestBody Long clientId)
    {
        return clientService.updateClient(client,clientId);
    }
}
