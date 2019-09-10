package com.przemo.rentcar.services;

import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.cars.CarDetails;

import java.util.List;
import java.util.Optional;


public interface CarService
{
    void deleteCarById(Long carId);

    void saveCarWithCarDetails(Car car, CarDetails carDetails);

    List<Car> getAllCars();

    Optional<Car> getCarByIdLazy(Long Id);

    Car persistCar(Car car);

    CarDetails getCarDetailById(Long id);

    CarDetails updateCarDetail( CarDetails carDetails,  Long carId);

    Car addNewCarWithBrand(Car car, long brandId);
}
