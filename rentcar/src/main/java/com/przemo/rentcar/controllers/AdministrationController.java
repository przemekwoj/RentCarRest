package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Employee;
import com.przemo.rentcar.entities.users.Supervisor;
import com.przemo.rentcar.services.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/administration")
public class AdministrationController
{
    private final AdministrationService administrationService;

    @Autowired
    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    @GetMapping("administrations")
    public List<Administration> getAllAdministrations()
    {
        return administrationService.getAllAdministrations();
    }

    @GetMapping("/{id}")
    public Administration getAdministratorById(@PathVariable Long id)
    {
        return administrationService.getAdministrationById(id);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmplyees()
    {
        return administrationService.getAllEmployees();
    }

    @GetMapping("/getAdministationByMail/{email}")
    public Administration getIdBYMail(@PathVariable String email)
    {
        return administrationService.getAdministrationByEmail(email);
    }

    @GetMapping("supervisor")
    public List<Supervisor> getAllsupervisors()
    {
        return administrationService.getAllSupervisors();
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@Validated @RequestBody Employee employee,@PathVariable Long id)
    {
        return administrationService.updateEmployee(employee,id);
    }

    @PutMapping("/supervisor/{id}")
    public Supervisor updateSupervisor(@Validated  @RequestBody Supervisor supervisor,@PathVariable Long id)
    {
        return administrationService.updateSupervisor(supervisor,id);
    }

    @PostMapping("/employee")
    public Employee addEmployee(@Validated @RequestBody Employee employee)
    {
        return administrationService.addEmployee(employee);
    }

    @PostMapping("/supervisor")
    public Supervisor addSupervisor(@Validated @RequestBody Supervisor supervisor)
    {
        return  administrationService.addSupervisor(supervisor);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministratorById(@PathVariable Long id)
    {
        administrationService.deleteAdministrator(id);
    }
}

