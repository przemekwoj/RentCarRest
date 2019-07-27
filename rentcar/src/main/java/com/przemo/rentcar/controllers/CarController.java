package com.przemo.rentcar.controllers;

import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.cars.CarDetails;
import com.przemo.rentcar.repositoriesDB.CarRepository;
import com.przemo.rentcar.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Transactional
@RequestMapping("/car")
public class CarController
{

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public List<Car> getAllCars()
    {
     return carService.getAllCars();
    }

    @GetMapping("/{carId}")
    public Car getCarById(@PathVariable Long carId)
    {
        Car car = carService.getCarById(carId).get(); /// dodac wyjatek tutaj bo Optional typ
        return car;
    }

    @PostMapping("/car")
    public  Car addNewCar(@RequestBody Car car)
    {
        return carService.persistCar(car);
    }

    @DeleteMapping("/{carId}")
    public String deleteCarById(@PathVariable Long carId)
    {
        ///potem zmienic zeby zwracalo status zamiast Stringa
        carService.deleteCarById(carId);
        return "delete successfuly";
    }

    @PutMapping("/car")
    public String updateCarById(@RequestBody Car updatedCar)
    {
        carService.persistCar(updatedCar);
        return "car updated";
    }

    @GetMapping("/carDetail/{carId}")
    public CarDetails getCarDetailById(@PathVariable Long carId)
    {
        return carService.getCarDetailById(carId);
    }

    @PutMapping("carDetail/{carId}")
    public CarDetails updateCarDetail(@RequestBody CarDetails carDetails,@PathVariable Long carId)
    {
        carService.updateCarDetail(carDetails,carId);

        System.out.println("heheasdasd");
        return carDetails;
    }
}
