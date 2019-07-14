package com.przemo.rentcar.controllers;

import com.przemo.rentcar.cars.Brand;
import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.cars.CarDetails;
import com.przemo.rentcar.repositoriesDB.BrandRepository;
import com.przemo.rentcar.repositoriesDB.CarDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarRepository;
import com.przemo.rentcar.services.CarService;
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


    @GetMapping("/test")
    public String test()
    {

        return "dziaslaAAAssqweqwe";
    }


    @GetMapping("/test1")
    public Car test1()
    {
        CarDetails cd = carDetailsRepository.findById(1L).get();
        System.out.println(cd.toString());
        cd.getCar();
        System.out.println(cd.toString());
        return cd.getCar();
    }

}
