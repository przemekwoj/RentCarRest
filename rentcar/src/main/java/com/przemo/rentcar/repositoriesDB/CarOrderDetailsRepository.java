package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.orders.CarOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarOrderDetailsRepository extends JpaRepository<CarOrderDetails,Long> {
}
