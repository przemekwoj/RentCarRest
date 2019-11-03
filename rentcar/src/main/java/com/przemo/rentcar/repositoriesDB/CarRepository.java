package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.repositoriesDB.queries.CarQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long>
{
    @Query(CarQueries.getAllCars)
    List<Car> getAllCars();

    @Query(CarQueries.getCarByIdLazy)
    Optional<Car> getCarByIdLazy(@Param("id") Long id);

    Optional<Car> findByPlateNumber(String plateNumber);

    List<Car> findByAvailableTrue();



}
