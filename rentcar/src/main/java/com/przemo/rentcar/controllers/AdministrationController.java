package com.przemo.rentcar.controllers;

import com.przemo.rentcar.services.AdministrationService;
import com.przemo.rentcar.users.Administration;
import com.przemo.rentcar.users.Employee;
import com.przemo.rentcar.users.Supervisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/administration")
public class AdministrationController
{
    @Autowired
    private AdministrationService administrationService;

    @GetMapping("administrations")
    public List<Administration> getAllAdministrations()
    {
        return administrationService.getAllAdministrations();
    }

    @GetMapping("/{id}")
    public Administration getAdministratorById(@PathVariable Long id)
    {
        return administrationService.getAdministrationById(id).get();
    }

    @GetMapping("employees")
    public List<Employee> getAllEmplyees()
    {
        return administrationService.getAllEmployees();
    }

    @GetMapping("supervisor")
    public List<Supervisor> getAllsupervisors()
    {
        return administrationService.getAllSupervisors();
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable Long id)
    {
        return administrationService.updateEmployee(employee,id);
    }

    @PutMapping("/supervisor/{id}")
    public Supervisor updateSupervisor(@RequestBody Supervisor supervisor,@PathVariable Long id)
    {
        return administrationService.updateSupervisor(supervisor,id);
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        return administrationService.addEmployee(employee);
    }

    @PostMapping("/supervisor")
    public Supervisor addSupervisor(@RequestBody Supervisor supervisor)
    {
        return  administrationService.addSupervisor(supervisor);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministratorById(@PathVariable Long id)
    {
        administrationService.deleteAdministrator(id);
    }
}

