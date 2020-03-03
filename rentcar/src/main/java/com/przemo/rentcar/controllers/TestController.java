package com.przemo.rentcar.controllers;

import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Transactional
public class TestController
{
//    static List<Car> cars = new ArrayList<>();
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    private CarRepository carRepository;
//
//    @Autowired
//    private CarDetailsRepository carDetailsRepository;
//
//    @Autowired
//    private BrandRepository brandRepository;
//
//    @Autowired
//    private CarService carService;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private SupervisorRepository supervisorRepository;
//
//    @Autowired
//    private ClientService clientService;
//
//    @Autowired
//    private CarOrderRepository carOrderRepository;
//
//    @Autowired
//    private AdministrationRepository administrationRepository;
//
//    @Autowired
//    private CarOrderDetailsRepository carOrderDetailsRepository;
//
//
//
//    @GetMapping("/sposobZapisaniaZamowienieKlinetOrazEmplyeeZOrderemOrazOrderDetais")
//    public String sposobZapisaniaZamowienieKlinetOrazEmplyeeZOrderemOrazOrderDetais()
//    {
//        Client client = clientService.getOneClient(8L);
//
//        Employee employee = employeeRepository.findById(9L).get();
//
//        Car car = carService.getCarByIdLazy(7L);
//
//        CarOrder carOrder = new CarOrder();
//
//        CarOrderDetails carOrderDetails = new CarOrderDetails();
//
//
//        carOrderDetails.setPrice(13);
//
//        carOrder.setClient(client);
//
//        carOrder.setStuff(employee);
//
//        carOrderDetails.setCarOrder(carOrder);
//
//
//        carOrderDetailsRepository.save(carOrderDetails);
//
//
//        return "test";
//    }
//
//    @GetMapping("/test")
//    public String test()
//    {
//        Client client = clientService.getOneClient(8L);
//
//        Employee employee = employeeRepository.findById(9L).get();
//
//        Car car = carService.getCarByIdLazy(7L);
//
//        CarOrder carOrder = new CarOrder();
//
//        CarOrderDetails carOrderDetails = new CarOrderDetails();
//
//
//        carOrderDetails.setPrice(13);
//
//        carOrder.setClient(client);
//
//        carOrder.setStuff(employee);
//
//        carOrderDetails.setCarOrder(carOrder);
//
//
//        carOrderDetailsRepository.save(carOrderDetails);
//
//        carOrderDetails.setCar(car);
//
//
//        return "test";
//    }
//
//    @GetMapping("/test3")
//    public String test3()
//    {
//        Employee employee = new Employee();
//        Supervisor supervisor = new Supervisor();
//
//        employee.setFirstName("ad");
//        employee.setEmail("e@wp.pl");
//        employee.setPassword("e");
//
//        administrationRepository.save(employee);
//
//        supervisor.setEmail("s@wp.pl");
//        supervisor.setPassword("s");
//
//        administrationRepository.save(supervisor);
//        return  "test3";
//    }
//
//    @GetMapping("/test4")
//    public String test4()
//    {
//       return "asd";
//    }
//
//


}
