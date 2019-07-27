package com.przemo.rentcar.services;

import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.cars.CarDetails;
import com.przemo.rentcar.repositoriesDB.CarDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService
{
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarDetailsRepository carDetailsRepository;

    @Override
    public List<Car> getAllCars()
    {
        return carRepository.getAllCars();
    }

    @Override
    public Optional<Car> getCarById(Long Id) {
        return carRepository.getCarById(Id);
    }

    @Override
    public Car persistCar(Car car) {
        return  carRepository.save(car);
    }

    @Override
    public void saveCarWithCarDetails(Car car, CarDetails carDetails)
    {
        carDetails.setCar(car);
        carDetailsRepository.save(carDetails);
    }

    @Override
    public CarDetails updateCarDetail(CarDetails carDetails, Long carId)
    {
        Car car = carRepository.getCarById(carId).get();

        carDetails.setCarDetails_id(carId);

        car.setCarDetails(carDetails);

        carRepository.save(car);

        return carDetails;
    }

    @Override
    public CarDetails getCarDetailById(Long id) {
        CarDetails carDetails = carDetailsRepository.findById(id).get();
        carDetails.setCar(null);
        return carDetails;
    }

    @Override
    public void deleteCarById(Long carId)
    {
        carRepository.deleteById(carId);
    }
}
