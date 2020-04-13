package com.przemo.rentcar.logsAOP.controllersLogs;

import com.przemo.rentcar.dto.carsDto.CarDetailsDto;
import com.przemo.rentcar.dto.carsDto.CarDto;
import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.cars.CarDetails;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class CarControllerLogs {
    private final Logger log = LoggerFactory.getLogger(CarControllerLogs.class);

    @Pointcut("execution(* com.przemo.rentcar.controllers.CarController.*(..))")
    public void allMethods(){
    }
    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.CarController.getAllCars())")
    public void getAllCars() {
    }

    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.CarController.getAvailableCars())")
    public void getAvailableCars() {
    }

    @Pointcut("execution(public com.przemo.rentcar.dto.carsDto.CarDto com.przemo.rentcar.controllers.CarController.getCarById(Long))")
    public void getCarById() {
    }

    @Pointcut("execution(public com.przemo.rentcar.dto.carsDto.CarDetailsDto com.przemo.rentcar.controllers.CarController.getCarDetailById(Long))")
    public void getCarDetailById() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.cars.Car com.przemo.rentcar.controllers.CarController.addNewCarWithBrand(com.przemo.rentcar.entities.cars.Car,Long))")
    public void addNewCarWithBrand() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.cars.Car com.przemo.rentcar.controllers.CarController.addNewCar(com.przemo.rentcar.entities.cars.Car))")
    public void addNewCar() {
    }

    @Pointcut("execution(public void com.przemo.rentcar.controllers.CarController.deleteCarById(Long))")
    public void deleteCarById() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.cars.Car com.przemo.rentcar.controllers.CarController.deleteCarById(com.przemo.rentcar.entities.cars.Car))")
    public void updateCar() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.cars.CarDetails com.przemo.rentcar.controllers.CarController.updateCarDetail(com.przemo.rentcar.entities.cars.CarDetails,Long))")
    public void updateCarDetail() {
    }

    @Before("getAllCars()")
    public void logGetAllCars() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get all cars ", authentication.getName());
    }

    @AfterReturning(pointcut = "getAllCars()", returning = "cars")
    public void logReturnGetAllCars(List<CarDto> cars) {
        log.trace("all cars list returned");
        if (cars.isEmpty()) {
            log.warn("Returned cars list is empty");
        }
    }

    @Before("getAvailableCars()")
    public void logGetAvailableCars() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get all available cars ", authentication.getName());
    }

    @AfterReturning(pointcut = "getAvailableCars()", returning = "cars")
    public void logReturnGetAvailableCars(List<CarDto> cars) {
        log.trace("all available cars list returned");
        if (cars.isEmpty()) {
            log.warn("Returned available cars list is empty");
        }
    }

    @Before("getCarById()")
    public void logGetCarById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get car with id: {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getAvailableCars()", returning = "car")
    public void logReturnGetCarById(CarDto car) {
        log.trace("car returned : {}", car);
        if (car == null) {
            log.warn("Returned car is null");
        }
    }

    @Before("getCarDetailById()")
    public void logGetCarDetailById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get car details with id: {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getCarDetailById()", returning = "carDetailsDto")
    public void logReturnGetCarDetailById(CarDetailsDto carDetailsDto) {
        log.trace("carDetailsDto returned : {}", carDetailsDto);
        if (carDetailsDto == null) {
            log.warn("Returned carDetailsDto is null");
        }
    }

    @Before("addNewCarWithBrand()")
    public void logAddNewCarWithBrand(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to add car {} to brand with id: {} ", authentication.getName(), joinPoint.getArgs()[0], joinPoint.getArgs()[1]);
    }

    @AfterReturning(pointcut = "addNewCarWithBrand()", returning = "car")
    public void logReturnAddNewCarWithBrand(Car car) {
        log.trace("car added : {}", car);
        if (car == null) {
            log.warn("added car is null");
        }
    }

    @Before("addNewCar()")
    public void logAddNewCar(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to add car {}", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "addNewCar()", returning = "car")
    public void logReturnAddNewCar(Car car) {
        log.trace("car added : {}", car);
        if (car == null) {
            log.warn("added car is null");
        }
    }

    @Before("deleteCarById()")
    public void logDeleteCarById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , try to delete  car with id{}", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "addNewCar()")
    public void logReturnDeleteCarById() {
        log.warn("car deleted");
    }

    @Before("updateCar()")
    public void logUpdateCar(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , try to update car with values : {}", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "updateCar()", returning = "car")
    public void logReturnUpdateCar(Car car) {
        log.warn("car was updated {}", car);
        if (car == null) {
            log.warn("updated car is null");
        }
    }


    @Before("updateCarDetail()")
    public void logUpdateCarDetail(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , try to update car detail with values : {} , to car id {}", authentication.getName(), joinPoint.getArgs()[0], joinPoint.getArgs()[1]);
    }

    @AfterReturning(pointcut = "updateCarDetail()", returning = "carDetails")
    public void logReturnUpdateCarDetail(CarDetails carDetails) {
        log.warn("carDetails was updated {}", carDetails);
        if (carDetails == null) {
            log.warn("updated carDetails is null");
        }
    }

    @AfterThrowing(value = "allMethods()", throwing = "e")
    public void throwsErrorInCarController(Exception e){
        log.error(e.getMessage());
    }
}
