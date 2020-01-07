package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.cars.CarDetails;

import java.util.List;


public interface CarService
{
    void deleteCarById(Long carId);

    void saveCarWithCarDetails(Car car, CarDetails carDetails);

    List<Car> getAllCars();

    List<Car> getAvailableCars();

    Car getCarByIdLazy(Long Id);

    Car persistCar(Car car);

    CarDetails getCarDetailById(Long id);

    CarDetails updateCarDetail( CarDetails carDetails,  Long carId);

    Car addNewCarWithBrand(Car car, long brandId);

    Car findByPlateNumber(String plateNumber);

}
