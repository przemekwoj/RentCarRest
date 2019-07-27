package com.przemo.rentcar.services;

import com.przemo.rentcar.users.Administration;
import com.przemo.rentcar.users.Employee;
import com.przemo.rentcar.users.Supervisor;

import java.util.List;
import java.util.Optional;

public interface AdministrationService
{
    List<Administration> getAllAdministrations();

    List<Employee> getAllEmployees();

    List<Supervisor> getAllSupervisors();

    Optional<Administration> getAdministrationById(Long id);

    Employee addEmployee(Employee employee);

    Supervisor addSupervisor(Supervisor supervisor);

    Employee updateEmployee(Employee employee,Long id);

    Supervisor updateSupervisor(Supervisor supervisor,Long id);

    void deleteAdministrator(Long id);



}
