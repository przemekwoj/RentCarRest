package com.przemo.rentcar.services;

import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.cars.CarDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CarService
{
    void deleteCarById(Long carId);

    void saveCarWithCarDetails(Car car, CarDetails carDetails);

    List<Car> getAllCars();

    Optional<Car> getCarById(Long Id);

    Car persistCar(Car car);

    CarDetails getCarDetailById(Long id);
}
