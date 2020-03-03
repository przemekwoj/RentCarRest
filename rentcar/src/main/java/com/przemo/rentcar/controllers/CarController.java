package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.cars.CarDTO;
import com.przemo.rentcar.entities.cars.CarDetails;
import com.przemo.rentcar.entities.cars.CarDetailsDTO;
import com.przemo.rentcar.services.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/car")
public class CarController
{

    private final CarService carService;

    private final ModelMapper modelMapper;

    @Autowired
    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/cars")
    public List<CarDTO> getAllCars()
    {
        List<Car> cars = carService.getAllCars();
        return modelMapper.map(cars, new TypeToken<List<CarDTO>>(){}.getType());
    }

    @GetMapping("/AvailableCars")
    public List<CarDTO> getAvailableCars()
    {
        return modelMapper.map(carService.getAvailableCars(), new TypeToken<List<CarDTO>>(){}.getType());
    }

    @GetMapping("/{carId}")
    public CarDTO getCarById(@PathVariable Long carId)
    {
        Car car = carService.getCarByIdLazy(carId);
        return modelMapper.map(car,CarDTO.class);
    }

    @GetMapping("/carDetail/{carId}")
    public CarDetailsDTO getCarDetailById(@PathVariable Long carId)
    {
        CarDetails carDetails = carService.getCarDetailById(carId);
        return modelMapper.map(carDetails,CarDetailsDTO.class);
    }

    @PostMapping("/carWithBrand/{brandId}")
    public  Car addNewCarWithBrand(@RequestBody Car car, @PathVariable long brandId)
    {
        return carService.addNewCarWithBrand(car,brandId);
    }

    @PostMapping("/car")
    public  Car addNewCar(@RequestBody Car car)
    {
        return carService.persistCar(car);
    }

    @DeleteMapping("/{carId}")
    public void deleteCarById(@PathVariable Long carId)
    {
        carService.deleteCarById(carId);
    }

    @PutMapping("/car")
    public Car updateCar(@RequestBody Car updatedCar)
    {
       return carService.persistCar(updatedCar);
    }


    @PutMapping("carDetail/{carId}")
    public CarDetails updateCarDetail(@RequestBody CarDetails carDetails,@PathVariable Long carId)
    {
        return carService.updateCarDetail(carDetails,carId);
    }

}
