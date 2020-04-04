package com.przemo.rentcar.controllers;

import com.przemo.rentcar.dto.carsDto.CarDto;
import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.cars.CarDetails;
import com.przemo.rentcar.dto.carsDto.CarDetailsDto;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.services.CarService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CarControllerTest function testing")
public class CarControllerTest {

    @Mock
    CarService carService;

    private static ModelMapper modelMapper = new ModelMapper();

    private static  CarController carController;

    private static List<Car> cars ;

    private static Car car;

    @BeforeAll
    static void init(){

        car = new Car();
        car.setPlateNumber("123");
        car.setAvailable(true);
        car.setCarDetails(new CarDetails());
        car.setCarOrderDetails(new CarOrderDetails());
        car.setCar_id(1L);
        cars = Arrays.asList(car);

    }


    @Nested
    @DisplayName("testing get methods")
    class GetMethods {

        @Test
        @DisplayName("should return whole list of CarDto")
        void shouldReturnAllCars() {
            carController = new CarController(carService,modelMapper);
            when(carService.getAllCars()).thenReturn(cars);
            List<CarDto> result = carController.getAllCars();
            assertEquals(cars.size(),result.size());
        }

        @Test
        @DisplayName("should return whole list of CarDto")
        void shouldReturnCars()
        {
            carController = new CarController(carService,modelMapper);
            when(carService.getAvailableCars()).thenReturn(cars);
            List<CarDto> result = carController.getAvailableCars();
            assertEquals(cars.size(),result.size());
        }

        @Test
        @DisplayName("should return car with particular id")
        void shouldReturnCarWithParticularId(){
            carController = new CarController(carService,modelMapper);
            when(carService.getCarByIdLazy(1L)).thenReturn(car);
            CarDto actual = carController.getCarById(1L);
            assertAll("check all fields",
                    ()->assertEquals(car.getCar_id(),actual.getCar_id()),
                    ()->assertEquals(car.getPlateNumber(),actual.getPlateNumber())
            );
        }

        @Test
        @DisplayName("should return card details with car id")
        void shouldReturnCarDetailsWithThisCarId()
        {
            carController = new CarController(carService,modelMapper);
            CarDetails carDetails = new CarDetails();
            carDetails.setCar(new Car());
            carDetails.setCarDetails_id(1L);
            carDetails.setColor("yellow");
            carDetails.setHigh(123);
            carDetails.setWeight(123);
            when(carService.getCarDetailById(1L)).thenReturn(carDetails);
            CarDetailsDto actual = carController.getCarDetailById(1L);
            assertAll("check all fields",
                    ()->assertEquals(carDetails.getCarDetails_id(),actual.getCarDetails_id()),
                    ()->assertEquals(carDetails.getColor(),actual.getColor()),
                    ()->assertEquals(carDetails.getHigh(),actual.getHigh()),
                    ()->assertEquals(carDetails.getWeight(),actual.getWeight())
            );

        }

        }


    @Nested
    @DisplayName("testing post methods")
    class PostMethods {

        @Test
        @DisplayName("should add Car to brand with this Id")
        void shouldAddCarToBrandWithThisId() {
            carController = new CarController(carService,modelMapper);
            when(carService.addNewCarWithBrand(car,1L)).thenReturn(car);
            Car actualCar = carController.addNewCarWithBrand(car,1L);
            assertEquals(car,actualCar);

        }

        @Test
        @DisplayName("should add Car")
        void shouldAddCar() {
            carController = new CarController(carService,modelMapper);
            when(carService.persistCar(car)).thenReturn(car);
            Car actualCar = carController.addNewCar(car);
            assertEquals(car,actualCar);
        }
    }

    @Nested
    @DisplayName("testing put methods")
    class PutMethods {

        @Test
        @DisplayName("should updateCar")
        void shouldUpdateCar(){
            carController = new CarController(carService,modelMapper);
            when(carService.persistCar(car)).thenReturn(car);
            Car actualCar = carController.updateCar(car);
            assertEquals(car,actualCar);
        }

        @Test
        @DisplayName("update CarDetail")
        void ShouldUpdateCarDetail()
        {
            carController = new CarController(carService,modelMapper);
            CarDetails carDetails = new CarDetails();
            when(carService.updateCarDetail(carDetails,1L)).thenReturn(carDetails);
            CarDetails actualCarDetails = carController.updateCarDetail(carDetails,1L);
            assertEquals(carDetails,actualCarDetails);
        }
    }
}
