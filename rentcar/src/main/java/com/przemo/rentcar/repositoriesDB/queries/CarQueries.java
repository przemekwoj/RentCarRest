package com.przemo.rentcar.repositoriesDB.queries;

public interface CarQueries {
    String getAllCars = "SELECT NEW Car (car.car_id,car.plateNumber,car.available)FROM Car car";
    String getCarByIdLazy = "SELECT NEW Car (car.car_id,car.plateNumber,car.available)FROM Car car " +
            "WHERE car.car_id = :id";
}
