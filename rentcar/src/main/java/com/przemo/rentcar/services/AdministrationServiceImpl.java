package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Employee;
import com.przemo.rentcar.entities.users.Supervisor;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.AdministrationRepository;
import com.przemo.rentcar.repositoriesDB.EmployeeRepository;
import com.przemo.rentcar.repositoriesDB.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrationServiceImpl implements AdministrationService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private AdministrationRepository administrationRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Administration getAdministrationByEmail(String email) {
        return administrationRepository.getAdministrationByEmail(email)
                .orElseThrow(() -> new NotFoundEntity("Not found administration with email "+email));
    }

    @Override
    public Administration findByEmail(String email) {
        return administrationRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundEntity("Not found administration with email "+email));
    }

    @Override
    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }

    @Override
    public List<Administration> getAllAdministrations() {
        return administrationRepository.findAll();
    }

    @Override
    public Administration getAdministrationById(Long id) {
        return administrationRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntity("Not found administration with id "+id));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return administrationRepository.save(employee);
    }

    @Override
    public Supervisor addSupervisor(Supervisor supervisor) {
        return administrationRepository.save(supervisor);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        employee.setUser_id(id);
        return administrationRepository.save(employee);
    }

    @Override
    public Supervisor updateSupervisor(Supervisor supervisor, Long id) {
        supervisor.setUser_id(id);
        return administrationRepository.save(supervisor);
    }

    @Override
    public void deleteAdministrator(Long id) {
        administrationRepository.deleteById(id);
    }
}
