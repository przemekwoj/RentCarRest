package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.cars.Brand;
import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.cars.CarDetails;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.CarDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final BrandService brandService;

    private final CarDetailsRepository carDetailsRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, BrandService brandService, CarDetailsRepository carDetailsRepository) {
        this.carRepository = carRepository;
        this.brandService = brandService;
        this.carDetailsRepository = carDetailsRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    @Override
    public Car findByPlateNumber(String plateNumber) {
        return carRepository.findByPlateNumber(plateNumber)
                .orElseThrow(() -> new NotFoundEntity("Not found car with plate number " + plateNumber));
    }

    @Override
    public Car getCarByIdLazy(Long Id) {
        return carRepository.getCarByIdLazy(Id)
                .orElseThrow(() -> new NotFoundEntity("Not found car with id " + Id));
    }

    @Override
    public Car persistCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findByAvailableTrue();
    }


    @Override
    public Car addNewCarWithBrand(Car car, long brandId) {
        saveCar(car);
        addCarToBrand(car, brandId);
        return car;
    }

    private void addCarToBrand(Car car, long brandId) {
        Brand brand = brandService.getBrandById(brandId);
        brand.addCar(car);
        brandService.persistBrand(brand);
    }

    private void saveCar(Car car) {
        CarDetails carDetails = new CarDetails();
        carDetails.setCar(car);
        carDetailsRepository.save(carDetails);
    }

    @Override
    public void saveCarWithCarDetails(Car car, CarDetails carDetails) {
        carDetails.setCar(car);
        carDetailsRepository.save(carDetails);
    }

    @Override
    public CarDetails updateCarDetail(CarDetails carDetails, Long carId) {
        Car car = carRepository.getCarByIdLazy(carId).get();

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
    public void deleteCarById(Long carId) {
        carRepository.deleteById(carId);
    }

}
