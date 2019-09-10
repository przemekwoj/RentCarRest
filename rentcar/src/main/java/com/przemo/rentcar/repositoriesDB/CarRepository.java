package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long>
{
    @Query("SELECT NEW Car (car.car_id,car.plateNumber,car.available)FROM Car car")
    List<Car> getAllCars();

    @Query("SELECT NEW Car (car.car_id,car.plateNumber,car.available)FROM Car car " +
            "WHERE car.car_id = :id")
    Optional<Car> getCarByIdLazy(@Param("id") Long id);

    Optional<Car> findByPlateNumber(String plateNumber);

}
