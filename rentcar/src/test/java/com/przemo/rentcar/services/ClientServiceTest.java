package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.users.Client;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ClientService function testing")
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Nested
    @DisplayName("get methods test")
    class GetMethod {

        @Test
        @DisplayName("should return client by id")
        void shouldReturnClientById() {
            //given
            Client client = new Client();
            client.setUser_id(1L);
            when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
            //when
            Client actual = clientService.getOneClient(1L);
            //then
            assertAll(
                    () -> assertEquals(client, actual),
                    () -> assertEquals(client.getUser_id(), actual.getUser_id())
            );
        }

        @Test
        @DisplayName("should Throw No tFound Exception By Wrong Platenumber")
        void shouldThrowNotFoundExceptionByWrongPlatenumber() {
            when(clientRepository.findById(2L)).thenThrow(new NotFoundEntity("Not found"));
            assertThrows(NotFoundEntity.class, () -> clientRepository.findById(2L));
        }
    }

    @Nested
    @DisplayName("modyfied methods test")
    class ModyfiedTest {


        @Test
        @DisplayName("should update client with id")
        void shouldUpdateClientWithId(){
            //given
            Client client = new Client();
            client.setUser_id(1L);
            when(clientRepository.save(client)).thenReturn(client);
            //when
            Client actual = clientService.updateClient(client,2L);
            //then
            assertAll(
                    () -> assertEquals(client, actual),
                    () -> assertEquals(2L, actual.getUser_id().longValue())
            );
        }
    }
}
