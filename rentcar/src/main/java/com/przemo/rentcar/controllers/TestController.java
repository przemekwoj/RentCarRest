package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.repositoriesDB.*;
import com.przemo.rentcar.services.CarService;
import com.przemo.rentcar.services.ClientService;
import com.przemo.rentcar.entities.users.Client;
import com.przemo.rentcar.entities.users.Employee;
import com.przemo.rentcar.entities.users.Supervisor;
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
    private ClientService clientService;

    @Autowired
    private CarOrderRepository carOrderRepository;

    @Autowired
    private AdministrationRepository administrationRepository;

    @Autowired
    private CarOrderDetailsRepository carOrderDetailsRepository;



    @GetMapping("/sposobZapisaniaZamowienieKlinetOrazEmplyeeZOrderemOrazOrderDetais")
    public String sposobZapisaniaZamowienieKlinetOrazEmplyeeZOrderemOrazOrderDetais()
    {
        Client client = clientService.getOneClient(8L);

        Employee employee = employeeRepository.findById(9L).get();

        Car car = carService.getCarByIdLazy(7L);

        CarOrder carOrder = new CarOrder();

        CarOrderDetails carOrderDetails = new CarOrderDetails();


        carOrderDetails.setPrice(13);

        carOrder.setClient(client);

        carOrder.setStuff(employee);

        carOrderDetails.setCarOrder(carOrder);


        carOrderDetailsRepository.save(carOrderDetails);


        return "test";
    }

    @GetMapping("/test")
    public String test()
    {
        Client client = clientService.getOneClient(8L);

        Employee employee = employeeRepository.findById(9L).get();

        Car car = carService.getCarByIdLazy(7L);

        CarOrder carOrder = new CarOrder();

        CarOrderDetails carOrderDetails = new CarOrderDetails();


        carOrderDetails.setPrice(13);

        carOrder.setClient(client);

        carOrder.setStuff(employee);

        carOrderDetails.setCarOrder(carOrder);


        carOrderDetailsRepository.save(carOrderDetails);

        carOrderDetails.setCar(car);


        return "test";
    }

    @GetMapping("/test3")
    public String test3()
    {
        Employee employee = new Employee();
        Supervisor supervisor = new Supervisor();

        employee.setFirstName("ad");
        employee.setEmail("e@wp.pl");
        employee.setPassword("e");

        administrationRepository.save(employee);

        supervisor.setEmail("s@wp.pl");
        supervisor.setPassword("s");

        administrationRepository.save(supervisor);
        return  "test3";
    }

    @GetMapping("/test4")
    public String test4()
    {
       return "asd";
    }




}
