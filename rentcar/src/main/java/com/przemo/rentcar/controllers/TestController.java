package com.przemo.rentcar.controllers;

import com.przemo.rentcar.cars.Brand;
import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.cars.CarDetails;
import com.przemo.rentcar.repositoriesDB.*;
import com.przemo.rentcar.services.CarService;
import com.przemo.rentcar.users.Administration;
import com.przemo.rentcar.users.Employee;
import com.przemo.rentcar.users.Supervisor;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
public class TestController
{
    static List<Car> cars = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarDetailsRepository carDetailsRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private AdministrationRepository administrationRepository;


    @GetMapping("/test")
    public String test()
    {
        Employee e = new Employee();
        e.setFirstName("asdasd");
        employeeRepository.save(e);

        Supervisor s = new Supervisor();
        s.setFirstName("aa");
        supervisorRepository.save(s);

        Employee e2 = new Employee();
        e2.setFirstName("admin");
        administrationRepository.save(e2);

        return "dziaslaAAAssqweqwe";
    }




}
