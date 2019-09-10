package com.przemo.rentcar.controllers;

import com.przemo.rentcar.cars.Brand;
import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.cars.CarDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2
{
    static Brand brand = new Brand();

    @GetMapping("/a")
    public String test()
    {
        System.out.println(brand.getBrand_id());
        System.out.println(brand.getAmountOfAvailableCars());
        System.out.println(brand.getCars().isEmpty());


        return "test22";
    }
}
