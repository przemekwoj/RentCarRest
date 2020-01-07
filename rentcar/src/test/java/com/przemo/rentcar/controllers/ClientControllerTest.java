package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.users.Client;
import com.przemo.rentcar.services.ClientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("BrandControllerTest function testing")
public class ClientControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    static private Client client = new Client();

    static  private List<Client> clients;

    @BeforeAll
    static void setUp(){
        client.setEmail("asd");
        client.setFirstName("asd");
        client.setLastName("last");
        client.setPassword("asd123");
        client.setPhone("123123123");
        client.setUser_id(1L);
        clients = Arrays.asList(client);
    }

    @Nested
    @DisplayName("testing get methods")
    class GetMethods {

        @Test
        @DisplayName("should return client by id")
        void shouldReturnClientById(){
            when(clientService.getOneClient(1L)).thenReturn(client);
            Client actual = clientController.getClientById(1L);
            assertAll(
                    "fields inside update employee",
                    ()->assertEquals(client.getEmail(),actual.getEmail()),
                    ()->assertEquals(client.getFirstName(),actual.getFirstName()),
                    ()->assertEquals(client.getLastName(),actual.getLastName()),
                    ()->assertEquals(client.getPassword(),actual.getPassword()),
                    ()->assertEquals(client.getPhone(),actual.getPhone()),
                    ()->assertEquals(client.getUser_id(),actual.getUser_id())
            );
        }

        @Test
        @DisplayName("should return all clients")
        void shouldReturnAllClients(){
            when(clientService.getAllClients()).thenReturn(clients);
            List<Client> actual = clientController.getClients();
            assertEquals(clients.size(),actual.size());

        }
    }
    @Nested
    @DisplayName("tessting post mehtods")
    class PostMethods{

        @Test
        @DisplayName("should add client")
        void shouldAddClient(){
            when(clientService.addClient(client)).thenReturn(client);
            Client actual = clientController.addClient(client);
            assertAll(
                    "fields inside update employee",
                    ()->assertEquals(client.getEmail(),actual.getEmail()),
                    ()->assertEquals(client.getFirstName(),actual.getFirstName()),
                    ()->assertEquals(client.getLastName(),actual.getLastName()),
                    ()->assertEquals(client.getPassword(),actual.getPassword()),
                    ()->assertEquals(client.getPhone(),actual.getPhone()),
                    ()->assertEquals(client.getUser_id(),actual.getUser_id())
            );

        }

        @Test
        @DisplayName("should update client")
        void shouldUpdateClient(){
            when(clientService.updateClient(client,1L)).thenReturn(client);
            Client actual = clientController.addClient(client,1L);
            assertAll(
                    "fields inside update employee",
                    ()->assertEquals(client.getEmail(),actual.getEmail()),
                    ()->assertEquals(client.getFirstName(),actual.getFirstName()),
                    ()->assertEquals(client.getLastName(),actual.getLastName()),
                    ()->assertEquals(client.getPassword(),actual.getPassword()),
                    ()->assertEquals(client.getPhone(),actual.getPhone()),
                    ()->assertEquals(client.getUser_id(),actual.getUser_id())
            );

        }
    }
}
