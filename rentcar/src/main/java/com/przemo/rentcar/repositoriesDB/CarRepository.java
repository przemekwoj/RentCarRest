package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.cars.Brand;
import com.przemo.rentcar.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long>
{
    @Query("SELECT NEW Car (car.car_id,car.plateNumer,car.available)FROM Car car")
    List<Car> getAllCars();

    @Query("SELECT NEW Car (car.car_id,car.plateNumer,car.available)FROM Car car " +
            "WHERE car.car_id = :id")
    Optional<Car> getCarById(@Param("id") Long id);

}
