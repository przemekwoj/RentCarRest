package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.entities.cars.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDetailsRepository extends JpaRepository<CarDetails,Long> {
}
