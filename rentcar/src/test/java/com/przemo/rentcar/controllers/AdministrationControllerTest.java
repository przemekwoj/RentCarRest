package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Employee;
import com.przemo.rentcar.entities.users.Supervisor;
import com.przemo.rentcar.services.AdministrationService;
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
@DisplayName("AdministrationController function testing")
public class AdministrationControllerTest {

    private static List<Administration> administrations;
    private static List<Employee> employees;
    private static List<Supervisor> supervisors;

    private static Administration administration1 = new Administration();
    private static Administration administration2 = new Administration();
    private static Employee employee1 = new Employee();
    private static Employee employee2 = new Employee();
    private static Supervisor supervisor1 = new Supervisor();
    private static Supervisor supervisor2 = new Supervisor();


    @InjectMocks
    private AdministrationController administrationController;

    @Mock
    private AdministrationService administrationService;

    @BeforeAll
    static void setUp(){
        administration1.setUser_id(1L);
        administration1.setEmail("email");
        administration2.setUser_id(2L);
        administration2.setEmail("email2");
        administrations = Arrays.asList(administration1,administration2);
        employees = Arrays.asList(employee1,employee2);
        supervisors = Arrays.asList(supervisor1,supervisor2);
    }

    @Nested
    @DisplayName("testing get methods")
    class GetMethods {

        @Test
        @DisplayName("should return whole list of administration")
        void testFindAdministrations() {
            when(administrationService.getAllAdministrations()).thenReturn(administrations);
            List<Administration> result = administrationController.getAllAdministrations();
            assertEquals(administrations.size(),result.size());
        }

        @Test
        @DisplayName("should return administration with particular id")
        void testGetByIdShouldReturnAdministration()
        {
            when(administrationService.getAdministrationById(1L)).thenReturn(administration1);
            Administration actual = administrationController.getAdministratorById(1L);
            assertEquals(administration1,actual);
        }

        @Test
        @DisplayName("should not return administration with particular id")
        void testGetByWrongIdShouldNotReturnEmplyee()
        {
            when(administrationService.getAdministrationById(8L)).thenReturn(null);
            Administration actual = administrationController.getAdministratorById(8L);
            assertEquals(null,actual);
        }

        @Test
        @DisplayName("should return whole list of employees")
        void testFindEmployees() {
            when(administrationService.getAllEmployees()).thenReturn(employees);
            List<Employee> result = administrationController.getAllEmployees();
            assertEquals(employees.size(),result.size());
        }

        @Test
        @DisplayName("should return administration with particular email")
        void testGetByEmailShouldReturnAdministration()
        {
            when(administrationService.getAdministrationByEmail("email")).thenReturn(administration1);
            Administration actual = administrationController.getIdByMail("email");
            assertEquals(administration1,actual);
        }

        @Test
        @DisplayName("should not return administration with particular email")
        void testGetByWrongEmailShouldNotReturnAdministration()
        {
            when(administrationService.getAdministrationByEmail("sss")).thenReturn(null);
            Administration actual = administrationController.getIdByMail("sss");
            assertEquals(null,actual);
        }

        @Test
        @DisplayName("should return whole list of supervisors")
        void testFindSupervisors() {
            when(administrationService.getAllSupervisors()).thenReturn(supervisors);
            List<Supervisor> result = administrationController.getAllSupervisors();
            assertEquals(supervisors.size(),result.size());
        }
    }

    @Nested
    @DisplayName("testing put methods")
    class PutMethods {

        @Test
        @DisplayName("should update employee")
        void shouldUpdateEmployee(){
            Employee employee = new Employee();
            employee.setEmail("email");
            employee.setFirstName("Przemo");
            employee.setLastName("emo");
            employee.setPassword("przemo123");
            employee.setPhone("123123123");
            employee.setUser_id(1L);

            when(administrationService.updateEmployee(employee,1L)).thenReturn(employee);

            Employee actual = administrationController.updateEmployee(employee,1L);

            assertAll(
                    "fields inside update employee",
                    ()->assertEquals(employee.getEmail(),actual.getEmail()),
                    ()->assertEquals(employee.getFirstName(),actual.getFirstName()),
                    ()->assertEquals(employee.getLastName(),actual.getLastName()),
                    ()->assertEquals(employee.getPassword(),actual.getPassword()),
                    ()->assertEquals(employee.getPhone(),actual.getPhone()),
                    ()->assertEquals(employee.getUser_id(),actual.getUser_id())
            );
        }

        @Test
        @DisplayName("should update supervisor")
        void shouldUpdateSupervisor(){
            Supervisor supervisor = new Supervisor();
            supervisor.setEmail("email");
            supervisor.setFirstName("Przemo");
            supervisor.setLastName("emo");
            supervisor.setPassword("przemo123");
            supervisor.setPhone("123123123");
            supervisor.setUser_id(1L);

            when(administrationService.updateSupervisor(supervisor,1L)).thenReturn(supervisor);

            Supervisor actual = administrationController.updateSupervisor(supervisor,1L);

            assertAll(
                    "fields inside update supervisor",
                    ()->assertEquals(supervisor.getEmail(),actual.getEmail()),
                    ()->assertEquals(supervisor.getFirstName(),actual.getFirstName()),
                    ()->assertEquals(supervisor.getLastName(),actual.getLastName()),
                    ()->assertEquals(supervisor.getPassword(),actual.getPassword()),
                    ()->assertEquals(supervisor.getPhone(),actual.getPhone()),
                    ()->assertEquals(supervisor.getUser_id(),actual.getUser_id())
            );
        }



    }

    @Nested
    @DisplayName("testing post methods")
    class PostMethods {

        @Test
        @DisplayName("should add employee")
        void shouldAddEmployee(){
            Employee employee = new Employee();
            employee.setEmail("email");
            employee.setFirstName("Przemo");
            employee.setLastName("emo");
            employee.setPassword("przemo123");
            employee.setPhone("123123123");

            when(administrationService.addEmployee(employee)).thenReturn(employee);

            Employee actual = administrationController.addEmployee(employee);

            assertAll(
                    "fields inside added employee",
                    ()->assertEquals(employee.getEmail(),actual.getEmail()),
                    ()->assertEquals(employee.getFirstName(),actual.getFirstName()),
                    ()->assertEquals(employee.getLastName(),actual.getLastName()),
                    ()->assertEquals(employee.getPassword(),actual.getPassword()),
                    ()->assertEquals(employee.getPhone(),actual.getPhone())
            );
        }

        @Test
        @DisplayName("should add supervisor")
        void shouldAddSupervisor(){
            Supervisor supervisor = new Supervisor();
            supervisor.setEmail("email");
            supervisor.setFirstName("Przemo");
            supervisor.setLastName("emo");
            supervisor.setPassword("przemo123");
            supervisor.setPhone("123123123");

            when(administrationService.addSupervisor(supervisor)).thenReturn(supervisor);

            Supervisor actual = administrationController.addSupervisor(supervisor);

            assertAll(
                    "fields inside added supervisor",
                    ()->assertEquals(supervisor.getEmail(),actual.getEmail()),
                    ()->assertEquals(supervisor.getFirstName(),actual.getFirstName()),
                    ()->assertEquals(supervisor.getLastName(),actual.getLastName()),
                    ()->assertEquals(supervisor.getPassword(),actual.getPassword()),
                    ()->assertEquals(supervisor.getPhone(),actual.getPhone())
            );
        }
    }

}
