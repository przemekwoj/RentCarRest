package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Employee;
import com.przemo.rentcar.entities.users.Supervisor;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.AdministrationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("AdministrationService function testing")
public class AdministrationServiceTest {

    @InjectMocks
    private AdministrationServiceImpl administrationService;

    @Mock
    private AdministrationRepository administrationRepository;

    private static Administration administration = new Administration();

    @BeforeAll
    static void setUp(){
        administration.setEmail("przemo@wp.pl");
    }

    @Nested
    @DisplayName("Testing get methods")
    class GetMethods{

        @Test
        @DisplayName("should Return administration with particular email")
        void shouldReturnAdministrationWithParticularEmail(){
            when(administrationRepository.getAdministrationByEmail("przemo@wp.pl")).thenReturn(Optional.of(administration));
            Administration actual = administrationService.getAdministrationByEmail("przemo@wp.pl");
            assertEquals(administration,actual);
        }

        @Test
        @DisplayName("should throw exception NF")
        void shouldThrowExceptionNotFoundByEmail(){
            when(administrationRepository.getAdministrationByEmail(anyString())).thenThrow( new NotFoundEntity("Not found administration with email "+anyString()));
            assertThrows(NotFoundEntity.class, () -> administrationService.getAdministrationByEmail("abc"));
        }

        @Test
        @DisplayName("should Return administration with particular email")
        void shouldReturnAdministrationFindByEmail(){
            when(administrationRepository.findByEmail("przemo@wp.pl")).thenReturn(Optional.of(administration));
            Administration actual = administrationService.findByEmail("przemo@wp.pl");
            assertEquals(administration,actual);
        }

        @Test
        @DisplayName("should throw exception NF")
        void shouldThrowExceptionNotFoundFindByEmail(){
            when(administrationRepository.findByEmail(anyString())).thenThrow( new NotFoundEntity("Not found administration with email "+anyString()));
            assertThrows(NotFoundEntity.class, () -> administrationService.findByEmail("abc"));
        }

        @Test
        @DisplayName("should Return administration by id")
        void shouldReturnAdministrationById(){
            when(administrationRepository.findById(1L)).thenReturn(Optional.of(administration));
            Administration actual = administrationService.getAdministrationById(1L);
            assertEquals(administration,actual);
        }

        @Test
        @DisplayName("should throw exception NF by id")
        void shouldThrowExceptionNotFoundFindById(){
            when(administrationRepository.findById(1L)).thenThrow( new NotFoundEntity("Not found administration with id "+1L));
            assertThrows(NotFoundEntity.class, () -> administrationService.getAdministrationById(1L));
        }
    }

    @Nested
    @DisplayName("Testing updated Methods")
    class UpdateMethods{

        @Test
        @DisplayName("should update employee with id")
        void shouldUpdateEmployeeWithId(){
            Employee employee = new Employee();
            when(administrationRepository.save(employee)).thenReturn(employee);
            Employee actual = administrationService.updateEmployee(employee,2L);
            assertAll(
                    ()->assertEquals(employee,actual),
                    ()->assertEquals(2L,actual.getUser_id().longValue())
            );
        }

        @Test
        @DisplayName("should update supervisor with id")
        void shouldUpdateSupervisorWithId(){
            Supervisor supervisor = new Supervisor();
            when(administrationRepository.save(supervisor)).thenReturn(supervisor);
            Supervisor actual = administrationService.updateSupervisor(supervisor,2L);
            assertAll(
                    ()->assertEquals(supervisor,actual),
                    ()->assertEquals(2L,actual.getUser_id().longValue())
            );
        }

    }
}
