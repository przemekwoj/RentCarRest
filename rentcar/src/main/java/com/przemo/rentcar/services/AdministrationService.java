package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Employee;
import com.przemo.rentcar.entities.users.Supervisor;

import java.util.List;

public interface AdministrationService
{
    List<Administration> getAllAdministrations();

    List<Employee> getAllEmployees();

    List<Supervisor> getAllSupervisors();

    Administration getAdministrationById(Long id);

    Employee addEmployee(Employee employee);

    Supervisor addSupervisor(Supervisor supervisor);

    Employee updateEmployee(Employee employee,Long id);

    Supervisor updateSupervisor(Supervisor supervisor,Long id);

    void deleteAdministrator(Long id);

    Administration findByEmail(String email);

    Administration getAdministrationByEmail(String email);


}
